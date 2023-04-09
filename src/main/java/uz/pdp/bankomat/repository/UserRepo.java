package uz.pdp.bankomat.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bankomat.entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {


    boolean existsByEmail(String email);

    Optional<User> findByEmailAndEmailCode(String email, String emailCode);

    Optional<User> findByEmail(String email);
}
