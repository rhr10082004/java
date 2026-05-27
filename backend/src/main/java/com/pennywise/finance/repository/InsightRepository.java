package com.pennywise.finance.repository;

import com.pennywise.finance.entity.Insight;
import com.pennywise.finance.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface InsightRepository extends JpaRepository<Insight, Long> {
    Page<Insight> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
    boolean existsByUserAndTypeAndMessageAndCreatedAtAfter(User user, String type, String message, LocalDateTime after);
}
