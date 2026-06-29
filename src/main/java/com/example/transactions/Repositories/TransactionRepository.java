package com.example.transactions.Repositories;

import com.example.transactions.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository  extends JpaRepository<Transaction,Long> {
    List<Transaction> findBySenderWalletId(Long senderWalletId);

    List<Transaction> findByReceiverWalletId(Long receiverWalletId);

    List<Transaction> findBySenderWalletIdOrReceiverWalletId(
            Long senderWalletId,
            Long receiverWalletId);
}

