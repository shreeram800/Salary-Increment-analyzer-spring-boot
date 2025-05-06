package org.example.salaryincrement.Repository;

import org.example.salaryincrement.Model.SalaryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface SalaryRecordRepository extends JpaRepository<SalaryRecord, Long> {
    Optional<SalaryRecord> findByCareerCareerIdAndEffectiveDate(Long userId, LocalDate date);
}
