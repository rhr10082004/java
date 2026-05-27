package com.pennywise.finance.util;

import java.util.Set;

public final class CategoryRules {
    private CategoryRules() {}

    public static final Set<String> ALLOWED = Set.of(
            "Food", "Shopping", "Travel", "Bills", "Entertainment", "Health", "Education", "Other"
    );
}
