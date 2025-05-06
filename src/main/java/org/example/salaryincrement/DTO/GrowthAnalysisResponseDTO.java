package org.example.salaryincrement.DTO;


import lombok.Data;
import org.example.salaryincrement.Model.Status;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GrowthAnalysisResponseDTO {

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
