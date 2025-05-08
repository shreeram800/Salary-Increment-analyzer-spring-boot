package org.example.salaryincrement.Repository;


import org.example.salaryincrement.Model.Career;
import org.example.salaryincrement.Model.SalaryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SalaryRecordRepository extends JpaRepository<SalaryRecord, Long> {

    List<SalaryRecord> findAllByCareerIn(List<Career> careers);

    List<SalaryRecord> findByCareer_CareerId(Long careerId);

    void deleteAllByCareer_CareerId(Long careerId);
}
