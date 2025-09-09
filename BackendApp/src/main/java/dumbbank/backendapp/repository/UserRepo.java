package dumbbank.backendapp.repository;

import dumbbank.backendapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends  JpaRepository<User, Long> {

//    Optional<User> getUserByUser_email(String email);
}
