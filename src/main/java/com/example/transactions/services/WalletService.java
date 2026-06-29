package com.example.transactions.services;

import com.example.transactions.DTO.BalanceResponse;
import com.example.transactions.Entities.Wallet;
import com.example.transactions.Repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;

@RequiredArgsConstructor
@Service
public class WalletService {

private final WalletRepository walletRepository;
private final RedisTemplate<String, Object> redisTemplate;

    public BalanceResponse getBalance(
            Long userId) {

        String key = "wallet:" + userId;

        BigDecimal cachedBalance =
                (BigDecimal) redisTemplate
                        .opsForValue()
                        .get(key);

        if (cachedBalance != null) {

            System.out.println("From Redis");

            return new BalanceResponse(
                    cachedBalance);
        }

        Wallet wallet =
                walletRepository
                        .findByUserId(userId)
                        .orElseThrow();

        redisTemplate
                .opsForValue()
                .set(
                        key,
                        wallet.getBalance(),
                        Duration.ofSeconds(30));

        System.out.println("From DB");

        return new BalanceResponse(
                wallet.getBalance());
    }

}