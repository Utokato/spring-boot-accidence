package cn.llman.controller;

import cn.llman.entity.User;
import cn.llman.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author
 * @date 2019/1/13
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(new User());

    }

    @GetMapping("/user")
    public User insertUser(User user) {
        return userRepository.save(user);
    }
}
