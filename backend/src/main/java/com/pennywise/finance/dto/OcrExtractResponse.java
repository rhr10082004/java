package com.pennywise.finance.dto;

public record OcrExtractResponse(
        String amount,
        String merchant,
        String date,
        String rawText
) {}
