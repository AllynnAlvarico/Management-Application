package dumbbank.backendapp.service;

import dumbbank.backendapp.model.User;
import dumbbank.backendapp.repository.UserRepo;
import dumbbank.backendapp.util.UUIDGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getByUserId(Long id) {
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        user.setUserId(new UUIDGenerator().generateUserID(userRepository));
        return userRepository.save(user);
    }

    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }
}
