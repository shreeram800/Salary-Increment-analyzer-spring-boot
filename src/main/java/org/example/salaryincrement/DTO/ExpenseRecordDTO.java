package org.example.salaryincrement.DTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class ExpenseRecordDTO {

    private Long expenseRecordId;
    private Long careerId;
    private BigDecimal expenseAmount;
    private LocalDate expenseDate;
    private String expenseCategory;
    private String currency;

}
