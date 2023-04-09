package uz.pdp.bankomat.payload;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {

    @NotNull
    @Email
    private String username;
    @NotNull
    private String password;

}
