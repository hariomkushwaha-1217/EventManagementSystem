package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.dao.EventDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;
import com.eventmanagement.exception.NoRecordAvailableException;
import com.eventmanagement.exception.IdNotFoundException;

@Service
public class EventService {
    @Autowired
    private EventDao eventDao;

    // Insert a record (fixed typo)
    public ResponseEntity<ResponseStructure<Event>> createEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        Event savedEvent = eventDao.createEvent(event);
        ResponseStructure<Event> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("Event record is saved!");
        structure.setData(savedEvent);
        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

    // Fetch all Event Records (fixed message)
    public ResponseEntity<ResponseStructure<List<Event>>> getAllEvent() {
        List<Event> events = eventDao.getAllEvent();
        ResponseStructure<List<Event>> structure = new ResponseStructure<>();
        if (!events.isEmpty()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Event Records Found");
            structure.setData(events);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }

    public ResponseEntity<ResponseStructure<Event>> findEventById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Event> events = eventDao.getEventById(id);
        ResponseStructure<Event> structure = new ResponseStructure<>();
        if (events.isPresent()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Event record is fetched");
            structure.setData(events.get());
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Record not found");
        }
    }

    public ResponseEntity<ResponseStructure<Event>> updateEvent(Integer id, Event event) {
        if (id == null || event == null) {
            throw new IllegalArgumentException("ID and Venue cannot be null");
        }
        Optional<Event> existingEvent = eventDao.getEventById(id);
        if (existingEvent.isPresent()) {
            event.setId(id);
            Event updatedEvent = eventDao.updateEvent(event);
            ResponseStructure<Event> structure = new ResponseStructure<>();
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Event is updated");
            structure.setData(updatedEvent);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Event not found");
        }
    }

    public ResponseEntity<ResponseStructure<String>> deleteEventById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Event> opt = eventDao.getEventById(id);
        ResponseStructure<String> structure = new ResponseStructure<>();
        if (opt.isPresent()) {
            eventDao.deleteEventById(id);
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Event record deleted successfully");
            structure.setData("Deleted event with id " + id);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Record not found");
        }
    }

    public ResponseEntity<ResponseStructure<List<Attendee>>> getAttendeeByEventId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        List<Attendee> la = eventDao.getAttendeeByEventId(id);
        ResponseStructure<List<Attendee>> structure = new ResponseStructure<>();
        if (!la.isEmpty()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Records found");
            structure.setData(la);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }

    // Get Events By Organizer Id
    public ResponseEntity<ResponseStructure<List<Event>>> getEventsByOrganizerId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        List<Event> ls = eventDao.getEventsByOrganizerId(id);
        ResponseStructure<List<Event>> structure = new ResponseStructure<>();
        if (!ls.isEmpty()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Records found");
            structure.setData(ls);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }

    // Get Events by pagination and sorting
    public ResponseEntity<ResponseStructure<Page<Event>>> getEventByPaginationAndSorting(Integer pageNumber, Integer pageSize, String field) {
        if (pageNumber == null || pageSize == null || field == null) {
            throw new IllegalArgumentException("Pagination parameters cannot be null");
        }
        Page<Event> pages = eventDao.getEventByPaginationAndSorting(pageNumber, pageSize, field);
        ResponseStructure<Page<Event>> structure = new ResponseStructure<>();
        if (pages.hasContent()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Records retrieved by sorting");
            structure.setData(pages);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }
}