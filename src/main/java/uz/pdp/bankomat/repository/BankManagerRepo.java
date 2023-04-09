package uz.pdp.bankomat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bankomat.entity.BankManager;

public interface BankManagerRepo extends JpaRepository<BankManager,Integer> {


}
