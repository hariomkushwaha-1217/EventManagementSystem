package com.eventmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Registration;

public interface AttendeeRepository extends JpaRepository<Attendee, Integer> {
	
	//Fetch Employee by contact
	List<Attendee> findByContact(Long contact);
	
	@Query("select a from Attendee a where a.contact=?1")
	List<Attendee> getByContact(Long contact);
	
	
	@Query("select r from Registration r where r.attendee.name = ?1") 
	List<Registration> getRegisttrationByAttendee(String name);

}
