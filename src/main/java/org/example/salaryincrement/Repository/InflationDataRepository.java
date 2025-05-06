package org.example.salaryincrement.Repository;

import org.example.salaryincrement.Model.InflationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InflationDataRepository extends JpaRepository<InflationData, Long> {

    Optional<InflationData> findByApplicableDate(LocalDate applicableDate);

    boolean existsByApplicableDate(LocalDate applicableDate);

    List<InflationData> findByApplicableDateBetweenOrderByApplicableDateAsc(LocalDate startDate, LocalDate endDate);

}