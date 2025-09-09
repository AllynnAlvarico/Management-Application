package dumbbank.backendapp.controller;

import dumbbank.backendapp.model.User;
import dumbbank.backendapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/id/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getByUserId(id);
    }

    @PostMapping("/signup")
    public User addUser(@RequestBody User user) {return userService.addUser(user); }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.getByUserId(id);
        if (user.isPresent()) {
            userService.removeUser(id);
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

}
