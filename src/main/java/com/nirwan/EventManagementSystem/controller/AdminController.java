package com.nirwan.EventManagementSystem.controller;

import com.nirwan.EventManagementSystem.entity.User;
import com.nirwan.EventManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userService.getAll();
        if (all.isEmpty()) {
            return new ResponseEntity<>("No Users Found.", HttpStatus.NO_CONTENT);
        } else {
            List<String> names = all.stream().map(User::getUsername).collect(Collectors.toList());
            return new ResponseEntity<>(names, HttpStatus.OK);
        }
    }

    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user) {
        userService.saveAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
