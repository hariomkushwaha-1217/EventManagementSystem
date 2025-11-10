package com.eventmanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.eventmanagement.entity.Registration;
public interface RegistrationRepository extends JpaRepository<Registration, Integer>{
	
	@Query("select r from Registration r where r.attendee.name = ?1") 
	List<Registration> getRegistrationByAttendee(String name);
	
	
	@Query("select e.registrations from Event e where e.id=?1")
	List<Registration> getRegistrationByEventId(Integer id);
	
}
