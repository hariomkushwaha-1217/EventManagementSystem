package com.eventmanagement.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer>{
	
	@Query("select v.event from Venue v where v.id=?1") 
	List<Event> getEventsByVenueId(Integer id);
	
	@Query("select v from Venue v where v.location=?1")
	List<Venue> getVenueByLocation(String location);
}
