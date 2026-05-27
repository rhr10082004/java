package com.pennywise.finance.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequest(
    @NotBlank @Size(max = 40) String category,
    @NotNull @DecimalMin(value = "0.01") BigDecimal amount,
    @NotBlank @Size(max = 40) String paymentMethod,
    @NotNull LocalDate transactionDate,
    @Size(max = 255) String note
) {}
