package dumbbank.backendapp.controller;

import dumbbank.backendapp.model.User;
import dumbbank.backendapp.service.UserServiceImpl;
import dumbbank.backendapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/login")
//    public ResponseEntity<User> login(@RequestParam String user_email, @RequestParam String password) {
//        Optional<User> userOptional = userService.getByUserEmail(user_email);
//        if (userOptional.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        if(!userOptional.get().getPassword().equals(password)) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
//    }
}
