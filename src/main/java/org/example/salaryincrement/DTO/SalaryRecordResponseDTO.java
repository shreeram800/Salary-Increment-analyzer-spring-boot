package org.example.salaryincrement.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SalaryRecordResponseDTO {

    private Long salaryRecordId;
    private Long careerId;
    private BigDecimal salaryAmount;
    private LocalDate effectiveDate;
    private String currency;

}
