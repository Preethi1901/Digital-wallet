package com.example.transactions.DTO;

import lombok.Data;

import java.math.BigDecimal;



@Data
public class BalanceResponse {

    private BigDecimal balance;

    public BalanceResponse(BigDecimal balance) {
        this.balance = balance;
    }
}