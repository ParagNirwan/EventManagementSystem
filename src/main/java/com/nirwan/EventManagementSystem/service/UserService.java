package com.nirwan.EventManagementSystem.service;

import com.nirwan.EventManagementSystem.entity.User;
import com.nirwan.EventManagementSystem.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(ObjectId id) {
        userRepository.deleteById(String.valueOf(id));
    }

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);

    }


}
