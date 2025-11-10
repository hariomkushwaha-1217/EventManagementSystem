package com.eventmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer>{
	
	@Query("select r.attendee from Registration r where r.event.id = ?1") 
	List<Attendee> getAttendeeByEventId(Integer id);
	
	@Query("select o.event from Organizer o where o.id=?1") 
	List<Event> getEventsByOrganizerId(Integer id);
	
	
}
