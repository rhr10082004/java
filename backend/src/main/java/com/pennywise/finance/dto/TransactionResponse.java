package com.pennywise.finance.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(
    Long id,
    String category,
    BigDecimal amount,
    String paymentMethod,
    LocalDate transactionDate,
    String note
) {}
