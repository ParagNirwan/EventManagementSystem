package com.nirwan.EventManagementSystem.controller;

import com.nirwan.EventManagementSystem.entity.User;
import com.nirwan.EventManagementSystem.repository.UserRepository;
import com.nirwan.EventManagementSystem.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.Credentials;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());
        try {
            userService.deleteUser(user.getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("user deleted successfully :" + user.getUsername());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("find/{username}")
    public ResponseEntity<?> findUser(@PathVariable String username) {

        try {
            User user = userService.findByUsername(username);
            return ResponseEntity.status(HttpStatus.FOUND).body("User Found: " + user.getUsername());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found: " + username);
        }
    }

    @PutMapping("updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User oldDataUser = userService.findByUsername(authentication.getName());

        if (oldDataUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found: " + user.getUsername());
        }

        boolean updated = false;

        // Update password if new password is provided
        if (!user.getPassword().isEmpty()) {
            oldDataUser.setPassword(passwordEncoder.encode(user.getPassword()));
            updated = true;
        }

        // Update username if provided and different
        if (!user.getUsername().isEmpty() && !user.getUsername().equals(oldDataUser.getUsername())) {
            oldDataUser.setUsername(user.getUsername());
            updated = true;
        }

        // Update usertype if provided and different
        if (!user.getUsertype().isEmpty() && !user.getUsertype().equals(oldDataUser.getUsertype())) {
            oldDataUser.setUsertype(user.getUsertype());
            updated = true;
        }

        if (updated) {
            userService.updateUser(oldDataUser); // save without encoding again
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Settings have been updated for user " + oldDataUser.getUsername());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("No changes detected for user " + oldDataUser.getUsername());
        }
    }



}
