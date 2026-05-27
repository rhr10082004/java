package com.pennywise.finance.service;

import com.pennywise.finance.dto.TransactionRequest;
import com.pennywise.finance.dto.TransactionResponse;
import com.pennywise.finance.entity.Transaction;
import com.pennywise.finance.entity.User;
import com.pennywise.finance.repository.TransactionRepository;
import com.pennywise.finance.util.CategoryRules;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionResponse create(User user, TransactionRequest request) {
        validateCategory(request.category());
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(request.amount());
        transaction.setCategory(request.category());
        transaction.setPaymentMethod(request.paymentMethod());
        transaction.setTransactionDate(request.transactionDate());
        transaction.setNote(request.note());
        transaction.setCreatedAt(LocalDateTime.now());

        Transaction saved = transactionRepository.save(transaction);
        return toResponse(saved);
    }

    public Page<TransactionResponse> getByUser(User user, Pageable pageable) {
        return transactionRepository.findByUserOrderByTransactionDateDesc(user, pageable)
                .map(this::toResponse);
    }

    public TransactionResponse getById(Long id, User user) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));

        if (!transaction.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        return toResponse(transaction);
    }

    public TransactionResponse update(Long id, User user, TransactionRequest request) {
        validateCategory(request.category());
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));

        if (!transaction.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        transaction.setAmount(request.amount());
        transaction.setCategory(request.category());
        transaction.setPaymentMethod(request.paymentMethod());
        transaction.setTransactionDate(request.transactionDate());
        transaction.setNote(request.note());

        Transaction updated = transactionRepository.save(transaction);
        return toResponse(updated);
    }

    public void delete(Long id, User user) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));

        if (!transaction.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        transactionRepository.delete(transaction);
    }

    public List<TransactionResponse> getByCategory(User user, String category) {
        return transactionRepository.findByUserAndCategoryOrderByTransactionDateDesc(user, category)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getCategory(),
                transaction.getAmount(),
                transaction.getPaymentMethod(),
                transaction.getTransactionDate(),
                transaction.getNote()
        );
    }

    private void validateCategory(String category) {
        if (!CategoryRules.ALLOWED.contains(category)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category");
        }
    }
}
