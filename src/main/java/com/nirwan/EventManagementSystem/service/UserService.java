package com.nirwan.EventManagementSystem.service;

import com.nirwan.EventManagementSystem.entity.User;
import com.nirwan.EventManagementSystem.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private static  final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(ObjectId id) {
        userRepository.deleteById(String.valueOf(id));
    }

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);

    }

    public List<User> getAll(){
        return userRepository.findAll();
    }


    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUsertype("ADMIN");
        userRepository.save(user);
    }
}
