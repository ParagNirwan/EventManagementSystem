package com.nirwan.EventManagementSystem.repository;

import com.nirwan.EventManagementSystem.entity.Event;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, ObjectId> {
}
