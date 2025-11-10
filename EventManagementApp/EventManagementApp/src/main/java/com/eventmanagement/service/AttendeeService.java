package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.dao.AttendeeDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.exception.NoRecordAvailableException;
import com.eventmanagement.exception.IdNotFoundException;

@Service
public class AttendeeService {
    @Autowired
    private AttendeeDao attendeeDao;

    // Insert Attendee
    public ResponseEntity<ResponseStructure<Attendee>> registerAttendee(Attendee attendees) {
        if (attendees == null) {
            throw new IllegalArgumentException("Attendee cannot be null");
        }
        Attendee savedAttendees = attendeeDao.registerAttendee(attendees);
        ResponseStructure<Attendee> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("Attendee record is saved!");
        structure.setData(savedAttendees);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    // Fetch record by id
    public ResponseEntity<ResponseStructure<Attendee>> getAttendeeById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Attendee> opt = attendeeDao.getAttendeeById(id);
        ResponseStructure<Attendee> structure = new ResponseStructure<>();
        if (opt.isPresent()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("ID available in DB");
            structure.setData(opt.get());
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Record not found");
        }
    }

    // Fetch all attendee
    public ResponseEntity<ResponseStructure<List<Attendee>>> getAllAttendee() {
        List<Attendee> attendees = attendeeDao.getAllAttendee();
        ResponseStructure<List<Attendee>> structure = new ResponseStructure<>();
        if (!attendees.isEmpty()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Attendee Records Found");
            structure.setData(attendees);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }
    
    // Update
    public ResponseEntity<ResponseStructure<Attendee>> updateAttendee(Integer id, Attendee attendee) {
        if (id ==null || attendee == null) {
            throw new IllegalArgumentException("Attendee cannot be null");
        }
        Optional<Attendee> existingAttendee = attendeeDao.getAttendeeById(id);
        ResponseStructure<Attendee> structure = new ResponseStructure<>();
        if (existingAttendee.isPresent()) {
        		attendee.setId(id);
            Attendee updatedAttendee = attendeeDao.updateAttendee(attendee);
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Attendee record updated successfully");
            structure.setData(updatedAttendee);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } 
        else {
            throw new NoRecordAvailableException("Attendee not found!");
        }
    }

    // Delete a record by id
    public ResponseEntity<ResponseStructure<String>> deleteAttendeeById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Attendee> opt = attendeeDao.getAttendeeById(id);
        ResponseStructure<String> structure = new ResponseStructure<>();
        if (opt.isPresent()) {
            attendeeDao.deleteAttendeeById(id);
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Attendee record deleted successfully");
            structure.setData("Deleted Attendee with ID: " + id);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Record not found");
        }
    }

    // Get by contact
    public ResponseEntity<ResponseStructure<List<Attendee>>> getByContact(Long contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
        List<Attendee> attendees = attendeeDao.findByContact(contact);
        ResponseStructure<List<Attendee>> structure = new ResponseStructure<>();
        if (!attendees.isEmpty()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Attendee records retrieved");
            structure.setData(attendees);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }

    // Get attendee by pagination and sorting
    public ResponseEntity<ResponseStructure<Page<Attendee>>> getAttendeeByPaginationAndSorting(Integer pageNumber, Integer pageSize, String field) {
        if (pageNumber == null || pageSize == null || field == null) {
            throw new IllegalArgumentException("Pagination parameters cannot be null");
        }
        Page<Attendee> pages = attendeeDao.getAttendeeByPaginationAndSorting(pageNumber, pageSize, field);
        ResponseStructure<Page<Attendee>> structure = new ResponseStructure<>();
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