package dumbbank.backendapp.util;

import dumbbank.backendapp.model.User;
import dumbbank.backendapp.repository.UserRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UUIDGenerator {

    public String generateUserID(UserRepo userRepository) {
        List<User> listOfUsers = userRepository.findAll();
        Set<String> existingUserIds = new HashSet<>(); /** using HashSet makes checking for duplicates much faster */
        for (User user : listOfUsers) {
            existingUserIds.add(user.getUserId());
        }

        String newUserId;
        do {
            newUserId = UUID.randomUUID().toString();
        } while (existingUserIds.contains(newUserId));

        return newUserId;
    }

}

