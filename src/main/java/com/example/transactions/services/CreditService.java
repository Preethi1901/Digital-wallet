package com.example.transactions.services;

import com.example.transactions.DTO.CreditRequest;
import com.example.transactions.DTO.DebitRequest;
import com.example.transactions.Entities.Wallet;
import com.example.transactions.Exceptions.InsufficientBalanceException;
import com.example.transactions.GlobalExceptionHandler;
import com.example.transactions.Repositories.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreditService {
    private final WalletRepository walletRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Transactional
    public void credit(CreditRequest request) {

        Wallet wallet = walletRepository
                        .findByUserId(
                                request.getUserId())
                        .orElseThrow();

        wallet.setBalance(
                wallet.getBalance()
                        .add(request.getAmount()));

        walletRepository.save(wallet);

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

        wallet.setBalance(
                wallet.getBalance()
                        .subtract(request.getAmount()));

        walletRepository.save(wallet);

        redisTemplate.delete(
                "wallet:" + request.getUserId());
    }
}

