package com.example.transactions.Controllers;

import com.example.transactions.DTO.TransferRequest;
import com.example.transactions.services.IdempotencyService;
import com.example.transactions.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Tag(name="Payment API")
@SecurityRequirement(name = "Bearer Authentication")
public class PaymentController {

    private final PaymentService paymentService;
    private final IdempotencyService idempotencyService;

    @Operation(summary = "Transfer money between wallets")
    @PostMapping("/transfer")
    public String transfer(
            @RequestHeader("X-Idempotency-Key")
            String key,
            @Valid @RequestBody TransferRequest request){



        return paymentService.transfer(key, request);
    }

}
