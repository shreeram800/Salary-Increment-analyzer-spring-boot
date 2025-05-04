package org.example.salaryincrement.Controller;

import lombok.RequiredArgsConstructor;
import org.example.salaryincrement.DTO.SalaryRecordDTO;
import org.example.salaryincrement.DTO.SalaryRecordResponseDTO;
import org.example.salaryincrement.Service.SalaryRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salary-records")
@RequiredArgsConstructor
public class SalaryRecordController {

    private final SalaryRecordService service;

    @PostMapping
    public ResponseEntity<SalaryRecordResponseDTO> create(@RequestBody SalaryRecordDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaryRecordResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SalaryRecordResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaryRecordResponseDTO> update(@PathVariable Long id, @RequestBody SalaryRecordDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

