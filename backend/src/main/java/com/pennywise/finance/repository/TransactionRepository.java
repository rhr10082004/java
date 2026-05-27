package com.pennywise.finance.repository;

import com.pennywise.finance.entity.Transaction;
import com.pennywise.finance.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByUserOrderByTransactionDateDesc(User user, Pageable pageable);

    List<Transaction> findByUserAndTransactionDateBetween(User user, LocalDate startDate, LocalDate endDate);

    List<Transaction> findByUserAndCategoryOrderByTransactionDateDesc(User user, String category);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user = :user AND YEAR(t.transactionDate) = :year AND MONTH(t.transactionDate) = :month")
    BigDecimal getTotalByUserAndMonth(@Param("user") User user, @Param("year") int year, @Param("month") int month);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user = :user AND t.category = :category AND YEAR(t.transactionDate) = :year AND MONTH(t.transactionDate) = :month")
    BigDecimal getTotalByCategoryAndMonth(@Param("user") User user, @Param("category") String category, @Param("year") int year, @Param("month") int month);

    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t WHERE t.user = :user AND YEAR(t.transactionDate) = :year AND MONTH(t.transactionDate) = :month GROUP BY t.category")
    List<Object[]> getCategoryTotalsByMonth(@Param("user") User user, @Param("year") int year, @Param("month") int month);

    @Query("SELECT t.transactionDate, SUM(t.amount) FROM Transaction t WHERE t.user = :user AND t.transactionDate BETWEEN :startDate AND :endDate GROUP BY t.transactionDate ORDER BY t.transactionDate ASC")
    List<Object[]> getDailyTotalsBetween(@Param("user") User user, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT t.category, SUM(t.amount) as total FROM Transaction t WHERE t.user = :user AND YEAR(t.transactionDate) = :year AND MONTH(t.transactionDate) = :month GROUP BY t.category ORDER BY total DESC")
    List<Object[]> getCategoryTotalsSorted(@Param("user") User user, @Param("year") int year, @Param("month") int month);

    List<Transaction> findByUserAndTransactionDateBetweenOrderByAmountDesc(User user, LocalDate startDate, LocalDate endDate);
}
