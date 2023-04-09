package uz.pdp.bankomat.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistorDto {
    @Size(min = 3,max = 50)
    private String firstName;
    @Size(min = 3,max = 50)
    private String lastName;
    @Email
    private String email;
    @NotNull
    private String password;
}
