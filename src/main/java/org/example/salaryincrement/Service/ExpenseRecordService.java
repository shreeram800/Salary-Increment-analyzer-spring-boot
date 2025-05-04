package org.example.salaryincrement.Service;

import org.example.salaryincrement.DTO.ExpenseRecordDTO;
import org.example.salaryincrement.DTO.ExpenseRecordMapper;
import org.example.salaryincrement.Exceptions.CareerNotFoundException;
import org.example.salaryincrement.Exceptions.ExpenseRecordNotFoundException;
import org.example.salaryincrement.Model.Career;
import org.example.salaryincrement.Model.ExpenseCategory;
import org.example.salaryincrement.Model.ExpenseRecord;
import org.example.salaryincrement.Repository.CareerRepo;
import org.example.salaryincrement.Repository.ExpenseRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseRecordService {

    private final ExpenseRecordRepository expenseRecordRepository;
    private final ExpenseRecordMapper expenseRecordMapper;
    private final CareerRepo careerRepository;

    @Autowired
    public ExpenseRecordService(ExpenseRecordRepository expenseRecordRepository, ExpenseRecordMapper expenseRecordMapper, CareerRepo careerRepository) {
        this.expenseRecordRepository = expenseRecordRepository;
        this.expenseRecordMapper = expenseRecordMapper;
        this.careerRepository = careerRepository;
    }

    public ExpenseRecordDTO createExpenseRecord(ExpenseRecordDTO expenseRecordDTO) {
        ExpenseRecord expenseRecord = expenseRecordMapper.toEntity(expenseRecordDTO);
        expenseRecord = expenseRecordRepository.save(expenseRecord);
        return expenseRecordMapper.toDTO(expenseRecord);
    }


    public ExpenseRecordDTO getExpenseRecordById(Long id) {
        ExpenseRecord expenseRecord = expenseRecordRepository.findById(id)
                .orElseThrow(() -> new ExpenseRecordNotFoundException("ExpenseRecord not found with id " + id));
        return expenseRecordMapper.toDTO(expenseRecord);
    }

    public List<ExpenseRecordDTO> getAllExpenseRecords() {
        List<ExpenseRecord> expenseRecords = expenseRecordRepository.findAll();
        return expenseRecords.stream()
                .map(expenseRecordMapper::toDTO)
                .collect(Collectors.toList());
    }
    public ExpenseRecordDTO updateExpenseRecord(Long id, ExpenseRecordDTO updatedExpenseRecordDTO) {
        ExpenseRecord existingExpenseRecord = expenseRecordRepository.findById(id)
                .orElseThrow(() -> new ExpenseRecordNotFoundException("ExpenseRecord not found with id " + id));

        Career career = careerRepository.findById(updatedExpenseRecordDTO.getCareerId())
                .orElseThrow(() -> new CareerNotFoundException("Career not found with id " + updatedExpenseRecordDTO.getCareerId()));

        existingExpenseRecord.setCareer(career);
        existingExpenseRecord.setExpenseAmount(updatedExpenseRecordDTO.getExpenseAmount());
        existingExpenseRecord.setExpenseDate(updatedExpenseRecordDTO.getExpenseDate());
        existingExpenseRecord.setExpenseCategory(ExpenseCategory.valueOf(updatedExpenseRecordDTO.getExpenseCategory()));
        existingExpenseRecord.setCurrency(updatedExpenseRecordDTO.getCurrency());

        ExpenseRecord updatedExpenseRecord = expenseRecordRepository.save(existingExpenseRecord);
        return expenseRecordMapper.toDTO(updatedExpenseRecord);
    }

    public void deleteExpenseRecord(Long id) {
        if (!expenseRecordRepository.existsById(id)) {
            throw new ExpenseRecordNotFoundException("ExpenseRecord not found with id " + id);
        }
        expenseRecordRepository.deleteById(id);
    }
}
