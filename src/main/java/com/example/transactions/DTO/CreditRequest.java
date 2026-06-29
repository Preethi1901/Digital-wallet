package com.example.transactions.DTO;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditRequest {
    @NotNull
    private Long userId;
    @NotNull
    @DecimalMin(value = "0.01",message = "Credit amount must be greater than 0")
    private BigDecimal amount;
}
