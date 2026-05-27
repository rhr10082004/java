package com.pennywise.finance.service;

import com.pennywise.finance.dto.OcrExtractResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OcrService {
    private static final Pattern AMOUNT_PATTERN = Pattern.compile("(\\d+[\\.,]\\d{2})");
    private static final Pattern DATE_PATTERN = Pattern.compile("(\\d{2}[/-]\\d{2}[/-]\\d{2,4})");

    public OcrExtractResponse extract(MultipartFile file) throws IOException {
        // Lightweight MVP parser: attempts to parse printable text content and extract key fields.
        String raw = new String(file.getBytes(), StandardCharsets.UTF_8);
        if (raw.length() > 2000) {
            raw = raw.substring(0, 2000);
        }

        String amount = find(AMOUNT_PATTERN, raw);
        String date = find(DATE_PATTERN, raw);
        String merchant = file.getOriginalFilename() == null ? "Unknown Merchant" : file.getOriginalFilename();

        return new OcrExtractResponse(amount, merchant, date, raw);
    }

    private String find(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(1) : "";
    }
}
