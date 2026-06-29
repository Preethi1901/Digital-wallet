package com.example.transactions.Controllers;

import com.example.transactions.DTO.CreditRequest;
import com.example.transactions.DTO.DebitRequest;
import com.example.transactions.services.CreditService;
import com.example.transactions.services.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreditController {
 private final CreditService creditService;
    //private final DebitService debitService;

    @PostMapping("/credit")
    public String credit(
            @Valid @RequestBody CreditRequest request) {

        creditService.credit(request);

        return "Amount Credited";
    }

    @PostMapping("/debit")
    public String debit(
            @Valid @RequestBody DebitRequest request) {

        creditService.debit(request);

        return "Amount Debited";
    }
}
