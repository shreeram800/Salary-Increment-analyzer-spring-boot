package org.example.salaryincrement.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateCareerRequest {
    private Long userId;
    private String careerName;
    private LocalDate careerStartDate;
}