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
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Venue;
import com.eventmanagement.service.VenueService;

@RestController
@RequestMapping("/venue")
public class VenueController {
    @Autowired
    private VenueService venueService;
    
    @PostMapping
    public ResponseEntity<ResponseStructure<Venue>> addVenue(@RequestBody Venue venues) {
        return venueService.addVenue(venues);
    }
    
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Venue>>> getAllVenue() {
        return venueService.getAllVenue();
    }
    
    // Fetch record by id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Venue>> getVenueById(@PathVariable Integer id) {
        return venueService.getVenueById(id);
    }
    
    // Update record by id
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Venue>> updateVenue(@PathVariable Integer id, @RequestBody Venue venue) {
        return venueService.updateVeune(id, venue);
    }
    
    // Delete a record by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteVenue(@PathVariable Integer id) {
        return venueService.deleteVenue(id);
    }
    
    @GetMapping("/venue_id/{id}")
    public ResponseEntity<ResponseStructure<List<Event>>> getEventByVenueId(@PathVariable Integer id) {
        return venueService.getEventsByVenueId(id);
    }
    
    @GetMapping("/location/{location}")
    public ResponseEntity<ResponseStructure<List<Venue>>> getVenueByLocation(@PathVariable String location) {
        return venueService.getVenueByLocation(location);
    }
    
    // Fetch Venue by Pagination with sorting
    @GetMapping("/page/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Venue>>> getVenueByPaginationAndSorting(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field) {
        return venueService.getVenueByPaginationAndSorting(pageNumber, pageSize, field);
    }
}
