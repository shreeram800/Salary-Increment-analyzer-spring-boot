package org.example.salaryincrement.Exceptions;

public class GrowthAnalysisException extends RuntimeException {

    public GrowthAnalysisException(String message) {
        super(message);
    }

    public GrowthAnalysisException(String message, Throwable cause) {
        super(message, cause);
    }
}
