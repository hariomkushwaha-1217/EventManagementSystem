package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;
import com.eventmanagement.repository.EventRepository;

@Repository
public class EventDao {
	@Autowired
	private EventRepository eventRepository;
	
	//Post Event
	public Event createEvent(Event event) {
		return eventRepository.save(event);
	}
	
	//Fetch All Event
	public List<Event> getAllEvent(){
		return eventRepository.findAll();
	}
	
	//Fetch Event by Event id
	public Optional<Event> getEventById(Integer id){
		return eventRepository.findById(id);
	}
	
	//Update
	public Event updateEvent(Event event) { 
		return eventRepository.save(event); 
	}

	public void deleteEventById(Integer id){
		 eventRepository.deleteById(id);
	}
	
	public List<Attendee> getAttendeeByEventId(Integer id){
		return eventRepository.getAttendeeByEventId(id);
	}
	public List<Event> getEventsByOrganizerId(Integer id){
		return eventRepository.getEventsByOrganizerId(id);
	}
	public Page<Event> getEventByPaginationAndSorting(int pageNumber, int pageSize, String field) {
		return eventRepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(field).ascending()));
	}
}
