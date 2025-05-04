package org.example.salaryincrement.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InflationDataDTO {
    private Long inflationDataId;
    private LocalDate applicableDate;
    private BigDecimal inflationRate;
    private String source;

    // Getters and Setters
}
