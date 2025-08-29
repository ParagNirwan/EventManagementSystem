package com.nirwan.EventManagementSystem.controller;

import com.nirwan.EventManagementSystem.entity.User;
import com.nirwan.EventManagementSystem.repository.UserRepository;
import com.nirwan.EventManagementSystem.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.Credentials;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    //Create User
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @DeleteMapping("id/{username}/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String username, @PathVariable ObjectId userId) {

        try {
            userService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("find/{username}")
    public ResponseEntity<?> findUser(@PathVariable String username) {
        try {
            User user = userService.findByUsername(username);
            return ResponseEntity.status(HttpStatus.FOUND).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        User oldDataUser = userService.findByUsername(user.getUsername());
        if (oldDataUser != null) {
            oldDataUser.setPassword(!user.getPassword().isEmpty() && oldDataUser.getPassword().equals(user.getPassword()) ? user.getPassword() : oldDataUser.getPassword());
            oldDataUser.setUsername(user.getUsername().isEmpty() ? oldDataUser.getUsername() : user.getUsername());
            oldDataUser.setUsertype(user.getUsertype().isEmpty() ? oldDataUser.getUsertype() : user.getUsertype());
            userService.saveUser(oldDataUser);
            return ResponseEntity.status(HttpStatus.OK).body("Settings have been updated for user " +  user.getUsername());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + user.getUsername());

    }


}
