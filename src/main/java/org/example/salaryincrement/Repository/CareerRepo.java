package org.example.salaryincrement.Repository;

import org.example.salaryincrement.Model.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CareerRepo extends JpaRepository<Career, Long> {

    List<Career> findByUserUserId(Long userId);

    List<Career> findByCareerStartDate(LocalDate startDate);

}
