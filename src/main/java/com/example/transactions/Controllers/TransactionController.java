package com.example.transactions.Controllers;

import com.example.transactions.Entities.Transaction;
import com.example.transactions.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor

public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/{userId}")
    public List<Transaction> getTransactions(@PathVariable Long userId){

        return transactionService
                .getTransactions(userId);
    }
}
