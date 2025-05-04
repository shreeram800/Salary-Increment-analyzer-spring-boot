package org.example.salaryincrement.Controller;

import org.example.salaryincrement.DTO.ExpenseRecordDTO;
import org.example.salaryincrement.Service.ExpenseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseRecordController {

    private ExpenseRecordService expenseRecordService;

    @Autowired
    public ExpenseRecordController(ExpenseRecordService expenseRecordService) {
        this.expenseRecordService = expenseRecordService;
    }


    @PostMapping
    public ResponseEntity<ExpenseRecordDTO> createExpense(@RequestBody ExpenseRecordDTO expenseRecordDTO) {
        return ResponseEntity.ok(expenseRecordService.createExpenseRecord(expenseRecordDTO));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ExpenseRecordDTO> getExpense(@PathVariable Long id) {
        return ResponseEntity.ok(expenseRecordService.getExpenseRecordById(id));
    }


    @GetMapping
    public ResponseEntity<List<ExpenseRecordDTO>> getAllExpenses() {
        return ResponseEntity.ok(expenseRecordService.getAllExpenseRecords());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseRecordDTO> updateExpense(@PathVariable Long id, @RequestBody ExpenseRecordDTO expenseRecordDTO) {
        return ResponseEntity.ok(expenseRecordService.updateExpenseRecord(id, expenseRecordDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseRecordService.deleteExpenseRecord(id);
        return ResponseEntity.noContent().build();
    }
}
