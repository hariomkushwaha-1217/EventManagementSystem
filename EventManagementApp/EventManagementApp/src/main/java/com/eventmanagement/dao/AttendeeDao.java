package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Registration;
import com.eventmanagement.repository.AttendeeRepository;

@Repository
public class AttendeeDao {
    @Autowired
    private AttendeeRepository attendeeRepository;
    
    public Attendee registerAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }
    
    public List<Attendee> getAllAttendee() {
        return attendeeRepository.findAll();
    }
    
    public Optional<Attendee> getAttendeeById(Integer id) {
        return attendeeRepository.findById(id);
    }
    
    public Attendee updateAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }
    
    public void deleteAttendeeById(Integer id) {
        attendeeRepository.deleteById(id);
    }
    
    public List<Registration> getRegisttrationByAttendee(String name) {
        return attendeeRepository.getRegisttrationByAttendee(name);
    }
    
    public List<Attendee> findByContact(Long contact) {
        return attendeeRepository.findByContact(contact);
    }
    
    public Page<Attendee> getAttendeeByPaginationAndSorting(int pageNumber, int pageSize, String field) {
        return attendeeRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
    }
}