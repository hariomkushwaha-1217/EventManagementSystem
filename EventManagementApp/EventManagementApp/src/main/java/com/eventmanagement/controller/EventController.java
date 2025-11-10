package com.eventmanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;
import com.eventmanagement.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;
    
    // Insert a record
    @PostMapping
    public ResponseEntity<ResponseStructure<Event>> createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }
    
    // Get all event
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Event>>> getAllEvent() {
        return eventService.getAllEvent();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Event>> findEventById(@PathVariable Integer id) {
        return eventService.findEventById(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Event>> updateEvent(@PathVariable Integer id,@RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }
    
    // Delete a record by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteEvent(@PathVariable Integer id) {
        return eventService.deleteEventById(id);
    }
    
    // Get Events By Organizer id
    @GetMapping("/organizer/{id}")
    public ResponseEntity<ResponseStructure<List<Event>>> getEventsByOrganizerId(@PathVariable Integer id) {
        return eventService.getEventsByOrganizerId(id);
    }
    
    @GetMapping("/attendee/{id}")
    public ResponseEntity<ResponseStructure<List<Attendee>>> getAttendeeByEventId(@PathVariable Integer id) {
        return eventService.getAttendeeByEventId(id);
    }
    
    // Get Attendee by pagination and sorting
    @GetMapping("/page/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Event>>> getEventByPaginationAndSorting(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field) {
        return eventService.getEventByPaginationAndSorting(pageNumber, pageSize, field);
    }
}
