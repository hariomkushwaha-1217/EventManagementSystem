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
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.service.AttendeeService;

@RestController
@RequestMapping("/attendee")
public class AttendeeController {
	
	@Autowired
	private AttendeeService attendeeService;
	
	//Insert a Attendee Record
	@PostMapping
	public ResponseEntity<ResponseStructure<Attendee>> registerAttendee(@RequestBody Attendee attendee){
		return  attendeeService.registerAttendee(attendee);
	}
	//Fetch All Record
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Attendee>>> getAllAttendee(){
		return attendeeService.getAllAttendee();
	}
	//Fetch record by id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Attendee>> getAttendeeById(@PathVariable Integer id){
		return attendeeService.getAttendeeById(id);
	}
	
	//Update Record by Id
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<Attendee>> updateAttendee(@PathVariable Integer id, @RequestBody Attendee attendee) {
	    return attendeeService.updateAttendee(id, attendee);
	}
	//delete Record by id
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteAttendee(@PathVariable Integer id) {
	    return attendeeService.deleteAttendeeById(id);
	}
	//Fetch Attendee by Contact
	@GetMapping("/contact/{contact}")
	public ResponseEntity<ResponseStructure<List<Attendee>>> getByContact(@PathVariable Long contact){
		return attendeeService.getByContact(contact);
	}
	
	//Get Attendee by pagination and sorting
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Attendee>>> getAttendeeByPaginationAndSorting(@PathVariable Integer pageNumber,@PathVariable Integer pageSize,@PathVariable String field){
		return attendeeService.getAttendeeByPaginationAndSorting(pageNumber,pageSize,field);
	}
}
