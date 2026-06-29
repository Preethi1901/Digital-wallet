package com.example.transactions.Controllers;

import com.example.transactions.DTO.LoginRequest;
import com.example.transactions.DTO.LoginResponse;
import com.example.transactions.services.LoginService;
import com.example.transactions.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



//package com.example.transactions.Controllers;

import com.example.transactions.DTO.LoginRequest;
import com.example.transactions.DTO.LoginResponse;
import com.example.transactions.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return loginService.login(request);
    }
}