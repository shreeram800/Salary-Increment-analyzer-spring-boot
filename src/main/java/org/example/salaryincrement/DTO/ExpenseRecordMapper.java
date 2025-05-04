package org.example.salaryincrement.DTO;

import org.example.salaryincrement.Model.Career;
import org.example.salaryincrement.Model.ExpenseCategory;
import org.example.salaryincrement.Model.ExpenseRecord;
import org.springframework.stereotype.Component;

@Component
public class ExpenseRecordMapper {

    public ExpenseRecordDTO toDTO(ExpenseRecord expenseRecord) {
        ExpenseRecordDTO dto = new ExpenseRecordDTO();
        dto.setExpenseRecordId(expenseRecord.getExpenseRecordId());
        dto.setCareerId(expenseRecord.getCareer().getCareerId());
        dto.setExpenseAmount(expenseRecord.getExpenseAmount());
        dto.setExpenseDate(expenseRecord.getExpenseDate());
        dto.setExpenseCategory(expenseRecord.getExpenseCategory().name());
        dto.setCurrency(expenseRecord.getCurrency());
        return dto;
    }

    public ExpenseRecord toEntity(ExpenseRecordDTO dto) {
        ExpenseRecord expenseRecord = new ExpenseRecord();
        Career career = new Career();
        career.setCareerId(dto.getCareerId());
        expenseRecord.setCareer(career);
        expenseRecord.setExpenseAmount(dto.getExpenseAmount());
        expenseRecord.setExpenseDate(dto.getExpenseDate());
        expenseRecord.setExpenseCategory(ExpenseCategory.valueOf(dto.getExpenseCategory()));
        expenseRecord.setCurrency(dto.getCurrency());
        return expenseRecord;
    }
}
