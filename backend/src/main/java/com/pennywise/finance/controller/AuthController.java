package com.pennywise.finance.controller;

import com.pennywise.finance.dto.auth.AuthResponse;
import com.pennywise.finance.dto.auth.LoginRequest;
import com.pennywise.finance.dto.auth.RegisterRequest;
import com.pennywise.finance.entity.User;
import com.pennywise.finance.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public Map<String, String> me(Principal principal) {
        User user = authService.getByEmail(principal.getName());
        return Map.of(
                "email", user.getEmail(),
                "name", user.getName()
        );
    }
}
