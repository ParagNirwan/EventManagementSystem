package com.nirwan.EventManagementSystem.service;

import com.nirwan.EventManagementSystem.entity.Event;
import com.nirwan.EventManagementSystem.entity.User;
import com.nirwan.EventManagementSystem.repository.EventRepository;
import com.nirwan.EventManagementSystem.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    public void saveEvent(Event event, String username) {
        event.setEventCreationDate(LocalDateTime.now());
        eventRepository.save(event);
        User user = userRepository.findUserByUsername(username);
        user.getEvents().add(event);
        userRepository.save(user);
    }

    public void deleteEvent(ObjectId eventId, String username) {
        eventRepository.deleteById(eventId);
        User user = userRepository.findUserByUsername(username);
        userRepository.save(user);
    }

    public Optional<Event> findEventById(ObjectId eventId) {
       return eventRepository.findById(eventId);
    }

}
