package com.pennywise.finance.controller;

import com.pennywise.finance.dto.TransactionRequest;
import com.pennywise.finance.dto.TransactionResponse;
import com.pennywise.finance.entity.User;
import com.pennywise.finance.service.AuthService;
import com.pennywise.finance.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final AuthService authService;

    public TransactionController(TransactionService transactionService, AuthService authService) {
        this.transactionService = transactionService;
        this.authService = authService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse create(@Valid @RequestBody TransactionRequest request, Principal principal) {
        User user = authService.getByEmail(principal.getName());
        return transactionService.create(user, request);
    }

    @GetMapping
    public Page<TransactionResponse> list(Pageable pageable, Principal principal) {
        User user = authService.getByEmail(principal.getName());
        return transactionService.getByUser(user, pageable);
    }

    @GetMapping("/{id}")
    public TransactionResponse getById(@PathVariable Long id, Principal principal) {
        User user = authService.getByEmail(principal.getName());
        return transactionService.getById(id, user);
    }

    @PutMapping("/{id}")
    public TransactionResponse update(@PathVariable Long id, @Valid @RequestBody TransactionRequest request, Principal principal) {
        User user = authService.getByEmail(principal.getName());
        return transactionService.update(id, user, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, Principal principal) {
        User user = authService.getByEmail(principal.getName());
        transactionService.delete(id, user);
    }

    @GetMapping("/category/{category}")
    public Object getByCategory(@PathVariable String category, Principal principal) {
        User user = authService.getByEmail(principal.getName());
        return transactionService.getByCategory(user, category);
    }
}
