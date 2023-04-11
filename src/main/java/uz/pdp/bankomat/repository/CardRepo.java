package uz.pdp.bankomat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bankomat.entity.Card;

public interface CardRepo extends JpaRepository<Card,Integer> {

        boolean existsByCardNumber(String cardNumber);

        boolean existsByCodeCVV(Integer codeCVV);

}
