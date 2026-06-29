package com.example.transactions.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
    public class TransactionResponse {

        private Long id;
        private Long senderWalletId;
        private Long receiverWalletId;
        private BigDecimal amount;
        private String status;
        private LocalDateTime createdAt;
    }

