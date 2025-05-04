package org.example.salaryincrement.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Entity
@Setter
@Table(name = "career")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "career_id")
    private Long careerId;

    @Column(name = "career_name")
    private String careerName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "career_start_date", nullable = false)
    private LocalDate careerStartDate;


    public Career() {
    }

    public Career(LocalDate careerStartDate) {
        this.careerStartDate = careerStartDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Career career = (Career) o;
        return Objects.equals(careerId, career.careerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(careerId);
    }

    @Override
    public String toString() {
        return "Career{" +
                "careerId=" + careerId +
                ", careerName='" + careerName + '\'' +
                ", user=" + user +
                ", careerStartDate=" + careerStartDate +
                '}';
    }
}