package uz.pdp.bankomat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bankomat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String cardType;
    @Column(nullable = false)
    private Integer maxAmount;
    private Integer remainingAmount;
    @Column(nullable = false)
    private Integer passwordCard;
    @Transient
    private Integer countFill;
    @Transient
    private Integer countPut;
    private boolean cardBlock = false;
    @Column(nullable = false)
    private String kupyura;


    public Integer getCountFill(Integer countFill) {
        return kupyura != null ? kupyura.length() : 0;
    }

    public Integer getCountPut(Integer countPut) {
        return kupyura != null ? kupyura.length() : 0;
    }
}
