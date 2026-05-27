package com.pennywise.finance.dto.auth;

public record AuthResponse(
        String token,
        String email,
        String name
) {
}
