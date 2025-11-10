package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Venue;
import com.eventmanagement.repository.VenueRepository;

@Repository
public class VenueDao {
    @Autowired
    private VenueRepository venueRepository;
    
    public Venue addVenue(Venue venues) {
        return venueRepository.save(venues);
    }
    
    public List<Venue> getAllVenue() {
        return venueRepository.findAll();
    }
    
    public Optional<Venue> getVenueById(Integer id) {
        return venueRepository.findById(id);
    }
    
    public Venue updateVenue(Venue venue) {
        return venueRepository.save(venue);
    }
    
    public void deleteVenue(Integer id) {
        venueRepository.deleteById(id);
    }
    
    public List<Event> getEventsByVenueId(Integer id) {
        return venueRepository.getEventsByVenueId(id);
    }
    
    public List<Venue> getVenueByLocation(String loc) {
        return venueRepository.getVenueByLocation(loc);
    }
    
    public Page<Venue> getVenueByPaginationAndSorting(int pageNumber, int pageSize, String field) {
        return venueRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
    }
}