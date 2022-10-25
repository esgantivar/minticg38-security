package tutorial.mintic.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tutorial.mintic.security.models.User;
import tutorial.mintic.security.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @PostMapping
    public User create(@RequestBody User data) {
        return this.userRepository.save(data);
    }
}
