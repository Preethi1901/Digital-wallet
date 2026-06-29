package com.example.transactions.Controllers;

import com.example.transactions.DTO.BalanceResponse;
import com.example.transactions.DTO.CreditRequest;
import com.example.transactions.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BalanceController {
    private final WalletService walletService;

    @GetMapping("/{userId}/balance")
    public BalanceResponse balance(@PathVariable Long userId){
        return walletService.getBalance(userId);
    }

}

