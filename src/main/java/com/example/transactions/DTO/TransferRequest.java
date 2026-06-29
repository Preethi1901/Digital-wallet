package com.example.transactions.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    @NotNull
    private Long senderUserId;
    @NotNull
    private Long receiverUserId;
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

}

