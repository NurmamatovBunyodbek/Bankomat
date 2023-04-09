package uz.pdp.bankomat.payload;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.bankomat.entity.Bankomat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDirectorDto {

    private String fullName;
    private List<Bankomat> bankomatList;

    private LocalDate inputHeadDay;

    private LocalDate outputHeadDay;

}
