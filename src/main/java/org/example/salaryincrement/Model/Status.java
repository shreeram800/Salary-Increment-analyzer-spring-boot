package org.example.salaryincrement.Model;

public enum Status {
    ABOVE_EXPECTATION,   // Actual growth is significantly higher than inflation
    MATCHED_INFLATION,   // Salary growth equals or closely matches inflation
    BELOW_EXPECTATION,   // Salary growth is less than inflation
    NO_DATA              // Not enough data to compute status
}