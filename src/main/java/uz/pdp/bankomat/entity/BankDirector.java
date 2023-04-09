package uz.pdp.bankomat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BankDirector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String fullName;
    @OneToMany
    private List<Bankomat> bankomatList;

    private LocalDate inputHeadDay;

    private LocalDate outputHeadDay;
    @Transient
    private Integer countInputDay;
    @Transient
    private Integer countOutputDay;

    public Integer getCountInputDay() {
        if (inputHeadDay == null)
            return 0;
        return Period.between(inputHeadDay, LocalDate.now()).getYears();
    }

    public Integer getCountOutputDay() {
        if (outputHeadDay == null)
            return 0;
        return Period.between(outputHeadDay, LocalDate.now()).getYears();
    }
}
