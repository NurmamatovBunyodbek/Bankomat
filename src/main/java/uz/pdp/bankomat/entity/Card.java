package uz.pdp.bankomat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false,unique = true)
    private String cardNumber;
    @Column(nullable = false)
    private String bankName;
    @Column(nullable = false)
    private String validity;
    @Column(nullable = false)
    private Integer code;
    @Column(nullable = false)
    private String cardType;
    @Column(nullable = false,unique = true)
    private Integer codeCVV;


}
