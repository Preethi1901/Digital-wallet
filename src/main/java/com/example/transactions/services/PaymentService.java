package com.example.transactions.services;

import com.example.transactions.DTO.PaymentCompletedEvent;
import com.example.transactions.DTO.TransferRequest;
import com.example.transactions.Entities.AuditLog;
import com.example.transactions.Entities.Transaction;
import com.example.transactions.Entities.Wallet;
import com.example.transactions.Exceptions.InsufficientBalanceException;
import com.example.transactions.PaymentEventProducer;
import com.example.transactions.Repositories.AuditRepository;
import com.example.transactions.Repositories.TransactionRepository;
import com.example.transactions.Repositories.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final IdempotencyService idempotencyService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final PaymentEventProducer paymentEventProducer;
    private final AuditRepository auditRepository;

    @Transactional
    public String transfer(String key, TransferRequest request) {

        Optional<String> existingResponse =
                idempotencyService.getExistingResponse(key);

        if (existingResponse.isPresent()) {
            return existingResponse.get();
        }

        Wallet sender = walletRepository
                .findByUserId(request.getSenderUserId())
                .orElseThrow();

        Wallet receiver = walletRepository
                .findByUserId(request.getReceiverUserId())
                .orElseThrow();

        if (sender.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }

        // Store balances before update
        BigDecimal senderBefore = sender.getBalance();
        BigDecimal receiverBefore = receiver.getBalance();

        // Update balances
        sender.setBalance(senderBefore.subtract(request.getAmount()));
        receiver.setBalance(receiverBefore.add(request.getAmount()));

        // Save updated wallets
        walletRepository.save(sender);
        walletRepository.save(receiver);

        // Sender audit log
        auditRepository.save(
                AuditLog.builder()
                        .wallet_Id(sender.getId())
                        .type("DEBIT")
                        .amount(request.getAmount())
                        .balanceBefore(senderBefore)
                        .balanceAfter(sender.getBalance())
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        // Receiver audit log
        auditRepository.save(
                AuditLog.builder()
                        .wallet_Id(receiver.getId())
                        .type("CREDIT")
                        .amount(request.getAmount())
                        .balanceBefore(receiverBefore)
                        .balanceAfter(receiver.getBalance())
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        // Clear Redis cache
        redisTemplate.delete("wallet:" + request.getSenderUserId());
        redisTemplate.delete("wallet:" + request.getReceiverUserId());

        // Save transaction
        Transaction tx = Transaction.builder()
                .senderWalletId(sender.getId())
                .receiverWalletId(receiver.getId())
                .amount(request.getAmount())
                .status("SUCCESS")
                .createdAt(LocalDateTime.now())
                .build();

        transactionRepository.save(tx);

        String response = "Transfer Successful";

        idempotencyService.saveResponse(key, response);

        // Publish Kafka event
        PaymentCompletedEvent event = PaymentCompletedEvent.builder()
                .senderUserId(request.getSenderUserId())
                .receiverUserId(request.getReceiverUserId())
                .amount(request.getAmount())
                .build();

        paymentEventProducer.publishPaymentCompleted(event);

        return response;
    }
}