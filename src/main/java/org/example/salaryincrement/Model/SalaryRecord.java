package org.example.salaryincrement.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "salary_records")
@Getter
@Setter
public class SalaryRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_record_id")
    private Long salaryRecordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id", nullable = false)
    private Career career;

    @Column(name = "salary_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal salaryAmount;

    @Column(name = "effective_date", nullable = false)
    private LocalDate effectiveDate;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    public SalaryRecord() {

    }

    public SalaryRecord(Career career, BigDecimal salaryAmount, LocalDate effectiveDate, String currency) {
        this.career = career;
        this.salaryAmount = salaryAmount;
        this.effectiveDate = effectiveDate;
        this.currency = currency;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryRecord that = (SalaryRecord) o;
        return Objects.equals(salaryRecordId, that.salaryRecordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salaryRecordId);
    }

    @Override
    public String toString() {
        return "SalaryRecord{" +
                "salaryRecordId=" + salaryRecordId +
                ", career=" + (career != null ? career.getCareerId() : null) + // Avoid loading the whole career entity in toString
                ", salaryAmount=" + salaryAmount +
                ", effectiveDate=" + effectiveDate +
                ", currency='" + currency + '\'' +
                '}';
    }
}