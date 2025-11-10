package com.eventmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Registration;
import com.eventmanagement.service.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	private RegistrationService registrationService;
	
	//create
	@PostMapping
	public ResponseEntity<ResponseStructure<Registration>> createRegistration(@RequestBody Registration reg){
		return registrationService.createRegistration(reg);
	}
	
	//get all record
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Registration>>> getAllRegistration() {
		 return registrationService.getAllRegistration();
	}
	
	//get record by id	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Registration>> getRegistrationById(@PathVariable Integer id) {
		return registrationService.getRegistrationById(id);
	}
	//cancel a record by id
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> cancelRegistration(@PathVariable Integer id) {
	    return registrationService.cancelRegistrationById(id);
	}
	
	@GetMapping("/event/id/{id}")
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByEventId(@PathVariable Integer id){
		return registrationService.getRegistrationByEventId(id);
	}
	
	@GetMapping("/attendee/{name}")
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByAttendee(@PathVariable String name){
		return registrationService.getRegistrationByAttendee(name);
	}
	
	 // Fetch Registration by Pagination with sorting
    @GetMapping("/page/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Registration>>> getRegistrationByPaginationAndSorting(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field) {
        return registrationService.getRegistrationByPaginationAndSorting(pageNumber, pageSize, field);
    }
}
