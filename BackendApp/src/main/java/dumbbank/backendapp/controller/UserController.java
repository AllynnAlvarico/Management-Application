package dumbbank.backendapp.controller;

import dumbbank.backendapp.model.User;
import dumbbank.backendapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/all users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/id/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getByUserId(id);
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) {
        System.out.println("Received User: " + user.getUser_email() + ", password: " + user.getPassword());
        return userService.addUser(user);
    }

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
