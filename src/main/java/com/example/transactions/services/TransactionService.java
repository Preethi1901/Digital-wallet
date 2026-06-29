package com.example.transactions.services;

import com.example.transactions.Entities.Transaction;
import com.example.transactions.Entities.Wallet;
import com.example.transactions.Repositories.TransactionRepository;
import com.example.transactions.Repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public List<Transaction> getTransactions(Long userId){

        Wallet wallet=walletRepository.findByUserId(userId)
                .orElseThrow(()->new RuntimeException("Wallet not found"));

        return transactionRepository.findBySenderWalletIdOrReceiverWalletId(wallet.getId(),wallet.getId());
    }
}
