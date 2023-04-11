package uz.pdp.bankomat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BankManager  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Column(nullable = false)
    private String fullName;
    @Email
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;




}
