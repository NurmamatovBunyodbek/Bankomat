package uz.pdp.bankomat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bankomat.entity.Bankomat;

public interface BankomatRepo extends JpaRepository<Bankomat,Integer> {
}
