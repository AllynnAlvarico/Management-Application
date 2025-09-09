package dumbbank.backendapp.service;

import dumbbank.backendapp.model.User;
import dumbbank.backendapp.repository.UserRepo;
import dumbbank.backendapp.service.interfaces.UserService;
import dumbbank.backendapp.util.UUIDGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;

    @Autowired
    public UserServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getByUserId(Long id) {
        return userRepository.findById(id);
    }

//    @Override
//    public Optional<User> getByUserEmail(String email) {
//        return userRepository.getUserByUser_email(email);
//    }

    public User addUser(User user) {
        user.setUserId(new UUIDGenerator().generateUserID(userRepository));
        return userRepository.save(user);
    }

    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }
}
