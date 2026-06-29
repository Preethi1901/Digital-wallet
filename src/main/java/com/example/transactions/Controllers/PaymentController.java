package com.example.transactions.Controllers;

import com.example.transactions.DTO.TransferRequest;
import com.example.transactions.services.IdempotencyService;
import com.example.transactions.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final IdempotencyService idempotencyService;

    @PostMapping("/transfer")
    public String transfer(
            @RequestHeader("X-Idempotency-Key")
            String key,
            @Valid @RequestBody TransferRequest request){



        return paymentService.transfer(key, request);
    }

}
