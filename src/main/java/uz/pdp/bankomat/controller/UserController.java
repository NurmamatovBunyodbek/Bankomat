package uz.pdp.bankomat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bankomat.entity.User;
import uz.pdp.bankomat.payload.Result;
import uz.pdp.bankomat.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> userList() {
        List<User> allUser = userService.getAllUser();
        return allUser;
    }

    @GetMapping("/{id}")
    public User getId(@PathVariable Integer id) {
        User userId = userService.getUserId(id);
        return userId;
    }

    @PostMapping
    public Result add(@RequestBody User user) {
        Result result = userService.addUser(user);
        return result;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody User user) {
        Result result = userService.editingUser(id, user);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleted(@PathVariable Integer id) {
        Result result = userService.deletedUser(id);
        return result;
    }


}
