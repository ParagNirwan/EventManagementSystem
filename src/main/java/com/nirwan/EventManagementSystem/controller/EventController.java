package com.nirwan.EventManagementSystem.controller;

import com.nirwan.EventManagementSystem.entity.Event;
import com.nirwan.EventManagementSystem.repository.EventRepository;
import com.nirwan.EventManagementSystem.service.EventService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {


    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/{username}")
    public ResponseEntity<?> createEvent(@RequestBody Event event, @PathVariable String username) {
        eventService.saveEvent(event, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @DeleteMapping("/{eventId}/{username}")
    public ResponseEntity<?> deleteEvent(@PathVariable ObjectId eventId, @PathVariable String username) {
        eventService.deleteEvent(eventId, username);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable ObjectId eventId, @RequestBody Event event) {

        Event oldEvent = eventService.findEventById(eventId).orElse(null);
        if (oldEvent != null) {
            oldEvent.setEventName(!event.getEventName().isEmpty() ? event.getEventName() : "");
            oldEvent.setEventDescription(event.getEventDescription() != null && !event.getEventDescription().isEmpty() ? event.getEventDescription() : "");
            eventRepository.save(oldEvent);
            return new ResponseEntity<>(oldEvent, HttpStatus.OK);
        }
        return new ResponseEntity<>(eventRepository.save(event), HttpStatus.OK);
    }

    @GetMapping("{eventId}")
    public ResponseEntity<?> getEventById(@PathVariable ObjectId eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            return new ResponseEntity<>(event.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
