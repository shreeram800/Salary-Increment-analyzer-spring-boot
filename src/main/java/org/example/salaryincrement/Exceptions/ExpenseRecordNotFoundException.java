package org.example.salaryincrement.Exceptions;

public class ExpenseRecordNotFoundException extends RuntimeException {
    public ExpenseRecordNotFoundException(String message) {
        super(message);
    }
}
