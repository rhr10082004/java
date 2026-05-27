package com.pennywise.finance.repository;

import com.pennywise.finance.entity.Budget;
import com.pennywise.finance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUserAndBudgetMonth(User user, String budgetMonth);

    Optional<Budget> findByUserAndCategoryAndBudgetMonth(User user, String category, String budgetMonth);

    List<Budget> findByUser(User user);

    @Query("SELECT COALESCE(SUM(b.limitAmount), 0) FROM Budget b WHERE b.user = :user AND b.budgetMonth = :budgetMonth")
    BigDecimal getTotalLimitByMonth(@Param("user") User user, @Param("budgetMonth") String budgetMonth);
}
