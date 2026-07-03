package com.example.transactions.services;

import com.example.transactions.DTO.CreditRequest;
import com.example.transactions.DTO.DebitRequest;
import com.example.transactions.Entities.AuditLog;
import com.example.transactions.Entities.Wallet;
import com.example.transactions.Exceptions.InsufficientBalanceException;
import com.example.transactions.GlobalExceptionHandler;
import com.example.transactions.Repositories.AuditRepository;
import com.example.transactions.Repositories.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CreditService {
    private final WalletRepository walletRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final AuditRepository auditRepository;

    @Transactional
    public void credit(CreditRequest request) {

        Wallet wallet = walletRepository
                        .findByUserId(
                                request.getUserId())
                        .orElseThrow();

//        wallet.setBalance(
//                wallet.getBalance()
//                        .add(request.getAmount()));
        //audit log
        BigDecimal before= wallet.getBalance();
        wallet.setBalance(
                before.add(request.getAmount())
        );


        walletRepository.save(wallet);

        auditRepository.save(
                AuditLog.builder()
                        .wallet_Id(wallet.getId())
                        .type("CREDIT")
                        .amount(request.getAmount())
                        .balanceBefore(before)
                        .balanceAfter(wallet.getBalance())
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        redisTemplate.delete(
                "wallet:" + request.getUserId());
    }

    @Transactional
    public void debit(DebitRequest request) {

        Wallet wallet = walletRepository.findByUserId(request.getUserId())
                        .orElseThrow();

        if (wallet.getBalance()
                .compareTo(request.getAmount()) < 0) {
            System.out.println("Insufficient Balance Exception Triggered");

            throw new InsufficientBalanceException(
                    "Insufficient Balance");
        }

//        wallet.setBalance(
//                wallet.getBalance()
//                        .subtract(request.getAmount()));

        //audit_log

        BigDecimal before = wallet.getBalance();

        wallet.setBalance(
                before.subtract(request.getAmount()));

        walletRepository.save(wallet);

        auditRepository.save(
                AuditLog.builder()
                        .wallet_Id(wallet.getId())
                        .type("DEBIT")
                        .amount(request.getAmount())
                        .balanceBefore(before)
                        .balanceAfter(wallet.getBalance())
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        //walletRepository.save(wallet);

        redisTemplate.delete(
                "wallet:" + request.getUserId());
    }
}

