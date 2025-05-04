package org.example.salaryincrement.Exceptions;

public class InflationDataNotFoundException extends RuntimeException {
    public InflationDataNotFoundException(String message) {
        super(message);
    }
}
