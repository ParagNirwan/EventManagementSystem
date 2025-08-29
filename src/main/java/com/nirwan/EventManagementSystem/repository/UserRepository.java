package com.nirwan.EventManagementSystem.repository;

import com.nirwan.EventManagementSystem.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findUserByUsername(String username);
}
