package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.dao.VenueDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Venue;
import com.eventmanagement.exception.NoRecordAvailableException;
import com.eventmanagement.exception.IdNotFoundException;

@Service
public class VenueService {
    @Autowired
    private VenueDao venueDao;

    // Fixed message: "Attendee" → "Venue"
    public ResponseEntity<ResponseStructure<Venue>> addVenue(Venue venues) {
        if (venues == null) {
            throw new IllegalArgumentException("Venue cannot be null");
        }
        Venue savedVenue = venueDao.addVenue(venues);
        ResponseStructure<Venue> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("Venue record is saved!");
        structure.setData(savedVenue);
        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<List<Venue>>> getAllVenue() {
        List<Venue> lv = venueDao.getAllVenue();
        ResponseStructure<List<Venue>> structure = new ResponseStructure<>();
        if (!lv.isEmpty()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Venue Records Found");
            structure.setData(lv);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }

    public ResponseEntity<ResponseStructure<Venue>> getVenueById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Venue> opt = venueDao.getVenueById(id);
        ResponseStructure<Venue> structure = new ResponseStructure<>();
        if (opt.isPresent()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("ID available in DB");
            structure.setData(opt.get());
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Record not found");
        }
    }
    
    
    public ResponseEntity<ResponseStructure<Venue>> updateVeune(Integer id, Venue venue) {
        if (id == null || venue == null) {
            throw new IllegalArgumentException("ID and Venue cannot be null");
        }
        Optional<Venue> existingEvent = venueDao.getVenueById(id);
        if (existingEvent.isPresent()) {
        		venue.setId(id); // Ensure ID is set
            Venue updatedVenue = venueDao.updateVenue(venue);
            ResponseStructure<Venue> structure = new ResponseStructure<>();
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Venue is updated");
            structure.setData(updatedVenue);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Venue not found");
        }
    }

    public ResponseEntity<ResponseStructure<String>> deleteVenue(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Venue> opt = venueDao.getVenueById(id);
        ResponseStructure<String> structure = new ResponseStructure<>();
        if (opt.isPresent()) {
            venueDao.deleteVenue(id);
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Venue deleted successfully");
            structure.setData("Deleted Venue with ID: " + id);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Venue with given ID not found");
        }
    }

    public ResponseEntity<ResponseStructure<List<Event>>> getEventsByVenueId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        List<Event> lv = venueDao.getEventsByVenueId(id);
        ResponseStructure<List<Event>> structure = new ResponseStructure<>();
        if (!lv.isEmpty()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Event records retrieved");
            structure.setData(lv);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }

    public ResponseEntity<ResponseStructure<List<Venue>>> getVenueByLocation(String loc) {
        if (loc == null || loc.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        List<Venue> lv = venueDao.getVenueByLocation(loc);
        ResponseStructure<List<Venue>> structure = new ResponseStructure<>();
        if (!lv.isEmpty()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Venue records retrieved");
            structure.setData(lv);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }

    // Fetch Venue by Pagination with sorting
    public ResponseEntity<ResponseStructure<Page<Venue>>> getVenueByPaginationAndSorting(Integer pageNumber, Integer pageSize, String field) {
        if (pageNumber == null || pageSize == null || field == null) {
            throw new IllegalArgumentException("Pagination parameters cannot be null");
        }
        Page<Venue> pages = venueDao.getVenueByPaginationAndSorting(pageNumber, pageSize, field);
        ResponseStructure<Page<Venue>> structure = new ResponseStructure<>();
        if (pages.hasContent()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Venue records retrieved by sorting");
            structure.setData(pages);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }
}