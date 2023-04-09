package uz.pdp.bankomat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.bankomat.entity.User;
import uz.pdp.bankomat.payload.Result;
import uz.pdp.bankomat.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User> getAllUser() {
        List<User> userList = userRepo.findAll();
        return userList;
    }

    public User getUserId(Integer id) {
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.get();
    }

    public Result addUser(User user) {
        User user1 = new User();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setPassword(user.getPassword());
        userRepo.save(user1);
        return new Result("User added", true);
    }

    public Result editingUser(Integer id, User user) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User user1 = optionalUser.get();
            user1.setFirstName(user.getFirstName());
            user1.setLastName(user.getLastName());
            user1.setPhoneNumber(user.getPhoneNumber());
            user1.setPassword(user.getPassword());
            userRepo.save(user1);
            return new Result("User editing", true);
        }
        return new Result("User not found", false);
    }

    public Result deletedUser(Integer id) {
        userRepo.deleteById(id);
        return new Result("User deleted", true);
    }
}
