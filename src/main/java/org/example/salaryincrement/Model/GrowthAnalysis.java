package org.example.salaryincrement.Model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GrowthAnalysis {

    private Long userId;
    private LocalDate startDate;
    private LocalDate endDate;

    private BigDecimal startingSalary;
    private BigDecimal endingSalary;

    private BigDecimal actualGrowthRate;
    private BigDecimal requiredGrowthRate;

    private Status status;

    private String summaryMessage;
}
