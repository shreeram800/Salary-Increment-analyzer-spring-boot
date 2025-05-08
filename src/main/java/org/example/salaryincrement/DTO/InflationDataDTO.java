package org.example.salaryincrement.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InflationDataDTO {
    private LocalDate applicableDate;
    private BigDecimal inflationRate;
    private String source;

}
