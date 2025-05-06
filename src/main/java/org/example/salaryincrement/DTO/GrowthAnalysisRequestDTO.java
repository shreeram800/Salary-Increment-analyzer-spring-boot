package org.example.salaryincrement.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GrowthAnalysisRequestDTO {
    private Long userId;
    private LocalDate startDate;
    private LocalDate endDate;
}
