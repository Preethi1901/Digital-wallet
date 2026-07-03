package com.example.transactions.Controllers;

import com.example.transactions.Entities.Transaction;
import com.example.transactions.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")

public class TransactionController {
    private final TransactionService transactionService;

    @Operation(summary = "Get All Transactions")
    @GetMapping("/{userId}")
    public List<Transaction> getTransactions(@PathVariable Long userId){

        return transactionService
                .getTransactions(userId);
    }
}
