package com.example.transactions.Controllers;

import com.example.transactions.DTO.BalanceResponse;
import com.example.transactions.DTO.CreditRequest;
import com.example.transactions.services.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name="Bearer Authentication")
public class BalanceController {
    private final WalletService walletService;

    @Operation(summary = "Get wallet balance")
    @GetMapping("/{userId}/balance")
    public BalanceResponse balance(@PathVariable Long userId){
        return walletService.getBalance(userId);
    }

}

