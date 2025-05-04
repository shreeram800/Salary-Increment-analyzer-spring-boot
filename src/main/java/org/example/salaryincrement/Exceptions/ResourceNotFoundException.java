package org.example.salaryincrement.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String careerNotFound) {
        super(careerNotFound);
    }
}
