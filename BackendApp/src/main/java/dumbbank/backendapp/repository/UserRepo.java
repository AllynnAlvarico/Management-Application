package dumbbank.backendapp.repository;

import dumbbank.backendapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends  JpaRepository<User, Long> {

}
