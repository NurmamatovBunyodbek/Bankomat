package uz.pdp.bankomat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bankomat.entity.Role;
import uz.pdp.bankomat.entity.enums.RoleName;


public interface RoleRepo extends JpaRepository<Role,Integer> {

    Role findByName(RoleName name);
}
