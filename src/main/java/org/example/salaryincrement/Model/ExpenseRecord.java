package org.example.salaryincrement.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "expense_records")
@Getter
@Setter
public class ExpenseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_record_id")
    private Long expenseRecordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id", nullable = false)
    private Career career;

    @Column(name = "expense_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal expenseAmount;

    @Column(name = "expense_date", nullable = false)
    private LocalDate expenseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_category", nullable = false)
    private ExpenseCategory expenseCategory;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    public ExpenseRecord() {
    }

    public ExpenseRecord(Career career, BigDecimal expenseAmount, LocalDate expenseDate, ExpenseCategory expenseCategory, String currency) {
        this.career = career;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
        this.expenseCategory = expenseCategory;
        this.currency = currency;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseRecord that = (ExpenseRecord) o;
        return Objects.equals(expenseRecordId, that.expenseRecordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expenseRecordId);
    }

    @Override
    public String toString() {
        return "ExpenseRecord{" +
                "expenseCategory=" + expenseCategory +
                ", expenseRecordId=" + expenseRecordId +
                ", career=" + career +
                ", expenseAmount=" + expenseAmount +
                ", expenseDate=" + expenseDate +
                ", currency='" + currency + '\'' +
                '}';
    }
}