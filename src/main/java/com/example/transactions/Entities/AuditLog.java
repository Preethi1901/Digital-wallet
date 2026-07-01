package com.example.transactions.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="audit_log")
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long wallet_Id;
    private String type; // CREDIT / DEBIT / TRANSFER

    private BigDecimal amount;

    private BigDecimal balanceBefore;

    private BigDecimal balanceAfter;

    private LocalDateTime createdAt;
}
