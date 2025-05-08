package org.example.salaryincrement.Service;

import org.example.salaryincrement.DTO.GrowthAnalysisRequestDTO;
import org.example.salaryincrement.DTO.GrowthAnalysisResponseDTO;
import org.example.salaryincrement.Exceptions.GrowthAnalysisException;
import org.example.salaryincrement.Model.*;
import org.example.salaryincrement.Repository.InflationDataRepository;
import org.example.salaryincrement.Repository.SalaryRecordRepository;
import org.example.salaryincrement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


@Service
public class GrowthAnalysisServiceImpl {

    private final InflationDataRepository inflationDataRepository;
    private final SalaryRecordRepository salaryRecordRepository;
    private final UserRepository userRepository;

    @Autowired
    public GrowthAnalysisServiceImpl(InflationDataRepository inflationDataRepository,
                                     SalaryRecordRepository salaryRecordRepository,
                                     UserRepository userRepository) {
        this.inflationDataRepository = inflationDataRepository;
        this.salaryRecordRepository = salaryRecordRepository;
        this.userRepository = userRepository;
    }

    public GrowthAnalysisResponseDTO analyzeGrowth(GrowthAnalysisRequestDTO requestDTO) {
        Long userId = requestDTO.getUserId();
        LocalDate startDate = requestDTO.getStartDate();
        LocalDate endDate = requestDTO.getEndDate();

        if (endDate.isBefore(startDate)) {
            throw new GrowthAnalysisException("End date cannot be before start date.");
        }

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new GrowthAnalysisException("User not found with ID " + userId));

        List<Career> careers = user.getCareers();

        if (careers == null || careers.isEmpty()) {
            throw new GrowthAnalysisException("No careers found for user ID " + userId);
        }

        List<SalaryRecord> allRecords = salaryRecordRepository.findAllByCareerIn(careers);

        List<SalaryRecord> recordsInRange = allRecords.stream()
                .filter(record -> !record.getEffectiveDate().isBefore(startDate) && !record.getEffectiveDate().isAfter(endDate))
                .sorted(Comparator.comparing(SalaryRecord::getEffectiveDate))
                .toList();

        if (recordsInRange.size() < 2) {
            throw new GrowthAnalysisException("Not enough salary records found between " + startDate + " and " + endDate);
        }

        SalaryRecord startRecord = recordsInRange.get(0); // earliest in range
        SalaryRecord endRecord = recordsInRange.get(recordsInRange.size() - 1); // latest in range

        BigDecimal startingSalary = startRecord.getSalaryAmount();
        BigDecimal endingSalary = endRecord.getSalaryAmount();

        BigDecimal actualGrowthRate = calculateGrowthRate(startingSalary, endingSalary);
        BigDecimal requiredGrowthRate = calculateCumulativeInflation(startDate, endDate);
        Status status = determineStatus(actualGrowthRate, requiredGrowthRate);

        return buildResponseDTO(userId, startDate, endDate, startingSalary, endingSalary,
                actualGrowthRate, requiredGrowthRate, status);
    }

    private BigDecimal calculateGrowthRate(BigDecimal start, BigDecimal end) {
        if (start.compareTo(BigDecimal.ZERO) <= 0) {
            throw new GrowthAnalysisException("Starting salary must be greater than zero.");
        }
        return end.subtract(start)
                .divide(start, 6, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateCumulativeInflation(LocalDate startDate, LocalDate endDate) {
        List<InflationData> inflationDataList = inflationDataRepository
                .findByApplicableDateBetweenOrderByApplicableDateAsc(startDate, endDate);

        if (inflationDataList.isEmpty()) {
            throw new GrowthAnalysisException("No inflation data found between " + startDate + " and " + endDate);
        }

        BigDecimal cumulativeFactor = BigDecimal.ONE;
        for (InflationData data : inflationDataList) {
            BigDecimal monthlyRate = data.getInflationRate()
                    .divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);
            cumulativeFactor = cumulativeFactor.multiply(BigDecimal.ONE.add(monthlyRate));
        }

        return cumulativeFactor.subtract(BigDecimal.ONE)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private Status determineStatus(BigDecimal actual, BigDecimal required) {
        if (actual.compareTo(required.add(BigDecimal.valueOf(2))) >= 0) {
            return Status.ABOVE_EXPECTATION;
        } else if (actual.compareTo(required) >= 0) {
            return Status.MATCHED_INFLATION;
        } else {
            return Status.BELOW_EXPECTATION;
        }
    }

    private GrowthAnalysisResponseDTO buildResponseDTO(Long userId, LocalDate startDate, LocalDate endDate,
                                                       BigDecimal startSalary, BigDecimal endSalary,
                                                       BigDecimal actual, BigDecimal required, Status status) {
        GrowthAnalysisResponseDTO dto = new GrowthAnalysisResponseDTO();
        dto.setUserId(userId);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        dto.setStartingSalary(startSalary);
        dto.setEndingSalary(endSalary);
        dto.setActualGrowthRate(actual);
        dto.setRequiredGrowthRate(required);
        dto.setStatus(status);
        dto.setSummaryMessage("Your salary growth is " +
                status.name().toLowerCase().replace("_", " ") +
                " compared to inflation.");
        return dto;
    }
}
