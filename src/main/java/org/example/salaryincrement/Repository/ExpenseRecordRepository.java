package org.example.salaryincrement.Repository;

import org.example.salaryincrement.Model.ExpenseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRecordRepository extends JpaRepository<ExpenseRecord, Long> {

}
