package org.example.salaryincrement.DTO;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SalaryRecordDTO {

    @NotNull
    private Long careerId;

    @NotNull
    private BigDecimal salaryAmount;

    @NotNull
    private LocalDate effectiveDate;

    private String currency;

}
