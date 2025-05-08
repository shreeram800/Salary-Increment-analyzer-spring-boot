package org.example.salaryincrement.Service;

import lombok.RequiredArgsConstructor;
import org.example.salaryincrement.DTO.SalaryRecordDTO;
import org.example.salaryincrement.DTO.SalaryRecordResponseDTO;
import org.example.salaryincrement.Exceptions.ResourceNotFoundException;
import org.example.salaryincrement.Model.Career;
import org.example.salaryincrement.Model.SalaryRecord;
import org.example.salaryincrement.Repository.CareerRepo;
import org.example.salaryincrement.Repository.SalaryRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalaryRecordService {

    private final SalaryRecordRepository repository;
    private final CareerRepo careerRepository;

    public SalaryRecordResponseDTO create(SalaryRecordDTO dto) {
        Career career = careerRepository.findById(dto.getCareerId())
                .orElseThrow(() -> new ResourceNotFoundException("Career not found with id: " + dto.getCareerId()));

        SalaryRecord record = new SalaryRecord();
        record.setCareer(career);
        record.setSalaryAmount(dto.getSalaryAmount());
        record.setEffectiveDate(dto.getEffectiveDate());
        record.setCurrency(dto.getCurrency());

        return toResponseDTO(repository.save(record));
    }

    public SalaryRecordResponseDTO getById(Long id) {
        SalaryRecord record = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salary record not found with id: " + id));
        return toResponseDTO(record);
    }

    public List<SalaryRecordResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public SalaryRecordResponseDTO update(Long id, SalaryRecordDTO dto) {
        SalaryRecord record = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salary record not found with id: " + id));

        Career career = careerRepository.findById(dto.getCareerId())
                .orElseThrow(() -> new ResourceNotFoundException("Career not found with id: " + dto.getCareerId()));

        record.setCareer(career);
        record.setSalaryAmount(dto.getSalaryAmount());
        record.setEffectiveDate(dto.getEffectiveDate());
        record.setCurrency(dto.getCurrency());

        return toResponseDTO(repository.save(record));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Salary record not found with id: " + id);
        }
        repository.deleteById(id);
    }
    public List<SalaryRecordResponseDTO> getSalaryRecordsByCareerId(Long careerId) {
        return repository.findByCareer_CareerId(careerId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

    }

    private SalaryRecordResponseDTO toResponseDTO(SalaryRecord record) {
        SalaryRecordResponseDTO dto = new SalaryRecordResponseDTO();
        dto.setSalaryRecordId(record.getSalaryRecordId());
        dto.setCareerId(record.getCareer().getCareerId());
        dto.setSalaryAmount(record.getSalaryAmount());
        dto.setEffectiveDate(record.getEffectiveDate());
        dto.setCurrency(record.getCurrency());
        return dto;
    }
}
