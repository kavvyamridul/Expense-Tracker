package com.example.expensemanagerapp;

public class ExpenseTransaction {
    private String amount;
    private String details;

    public ExpenseTransaction(String amount, String details) {
        this.amount = amount;
        this.details = details;
    }

    public String getAmount() {
        return amount;
    }

    public String getDetails() {
        return details;
    }
}
