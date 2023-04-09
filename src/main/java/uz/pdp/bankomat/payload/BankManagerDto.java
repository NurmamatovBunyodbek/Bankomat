package uz.pdp.bankomat.payload;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankManagerDto {


    private String fullName;

    private String email;

    private String password;

}
