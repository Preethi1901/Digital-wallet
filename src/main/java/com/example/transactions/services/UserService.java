package com.example.transactions.services;


import com.example.transactions.Entities.KycStatus;
import com.example.transactions.DTO.RegisterRequest;
import com.example.transactions.Entities.Wallet;
import com.example.transactions.Repositories.UserRepository;
import com.example.transactions.Repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.transactions.Entities.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletRepository walletRepository;

    public String register(RegisterRequest request) {


        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .kycStatus(KycStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        //userRepository.save(user);

        User savedUser = userRepository.save(user);

        walletRepository.save(
                Wallet.builder()
                        .userId(savedUser.getId())
                        .balance(BigDecimal.ZERO)
                        .currency("INR")
                        .build()
        );

        return "User Registered Successfully";

       // return "User Registered Successfully";
    }
}