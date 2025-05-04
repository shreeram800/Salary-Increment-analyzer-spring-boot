package org.example.salaryincrement.Model;

import lombok.Getter;

@Getter
public enum ExpenseCategory {
    ESSENTIAL("Essential"),
    DISCRETIONARY("Discretionary"),
    RENT("Rent"),
    MORTGAGE("Mortgage"),
    FOOD("Food"),
    UTILITIES("Utilities"),
    TRANSPORTATION("Transportation"),
    HEALTHCARE("Healthcare"),
    ENTERTAINMENT("Entertainment"),
    SAVINGS("Savings"),
    DEBT_PAYMENT("Debt Payment"),
    EDUCATION("Education"),
    PERSONAL_CARE("Personal Care"),
    MISCELLANEOUS("Miscellaneous");

    private final String displayName;

    ExpenseCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}