package org.example.salaryincrement.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "inflation_data")
public class InflationData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inflation_data_id")
    private Long inflationDataId;

    @Column(name = "applicable_date", nullable = false, unique = true)
    private LocalDate applicableDate;

    @Column(name = "inflation_rate", nullable = false, precision = 10, scale = 5)
    private BigDecimal inflationRate;

    @Column(name = "source", length = 100)
    private String source;


    public InflationData() {
    }

    public InflationData(LocalDate applicableDate, BigDecimal inflationRate, String source) {
        this.applicableDate = applicableDate;
        this.inflationRate = inflationRate;
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InflationData that = (InflationData) o;
        return Objects.equals(inflationDataId, that.inflationDataId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inflationDataId);
    }

    @Override
    public String toString() {
        return "InflationData{" +
                "inflationDataId=" + inflationDataId +
                ", applicableDate=" + applicableDate +
                ", inflationRate=" + inflationRate +
                ", source='" + source + '\'' +
                '}';
    }
}