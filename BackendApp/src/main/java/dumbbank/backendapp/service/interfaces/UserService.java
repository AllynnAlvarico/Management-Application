package dumbbank.backendapp.service.interfaces;

import dumbbank.backendapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getByUserId(Long id);
    Optional<User> getByUserEmail(String email);
    User addUser(User user);
    void removeUser(Long id);
}
