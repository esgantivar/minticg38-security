package tutorial.mintic.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tutorial.mintic.security.models.User;
import tutorial.mintic.security.repositories.UserRepository;
import tutorial.mintic.security.utils.Utils;

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
        /*
        upsert - > update or insert
         */
        String encryptedPassword = Utils.encryptSHA256(data.getPassword());
        data.setPassword(encryptedPassword);
        List<User> users = this.userRepository.findByUsername(data.getUsername());
        if(users.size() > 0) {
            User currentUser = users.get(0);
            currentUser.setEmail(data.getEmail());
            currentUser.setPassword(data.getPassword());
            return this.userRepository.save(currentUser);
        } else {
            return this.userRepository.save(data);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public User getById(@PathVariable String id) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user != null) {
            return user;
        }
        throw new UserNotFoundException();
    }

    @PutMapping("{id}")
    public User update(@PathVariable String id, @RequestBody User info) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setEmail(info.getEmail());
            user.setPassword(Utils.encryptSHA256(info.getPassword()));
            return this.userRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user != null) {
            this.userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("auth")
    public void auth(@RequestBody AuthInfo data) {
        List<User> users = this.userRepository.findByUsername(data.getUsername());
        if(users.size() > 0) {
            User currentUser = users.get(0);
            if(data.matchPassword(currentUser)) {
                /*
                camino feliz -> la contraseña que nos enviaron es la misma de la base de datos
                 */
                return;
            }
        }
        throw new AuthErrorException();
    }
}


@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class AuthErrorException extends RuntimeException {
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class PasswordNotMatchException extends RuntimeException {
}
class AuthInfo {
    private String username;
    private String password;

    public AuthInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean matchPassword(User user) {
        try {
            return Utils.encryptSHA256(this.password).equals(user.getPassword());
        } catch (NullPointerException e) {
            return false;
        }

    }

    public String getUsername() {
        return username;
    }
}