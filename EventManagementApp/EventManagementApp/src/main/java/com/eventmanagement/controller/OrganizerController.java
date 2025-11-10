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
import com.eventmanagement.entity.Organizer;
import com.eventmanagement.service.OrganizerService;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {
	@Autowired
	private OrganizerService organizerService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Organizer>> addOrganizer(@RequestBody Organizer org){
		return organizerService.AddOrganizer(org);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Organizer>>> getAllOrganizer(){
		return organizerService.getAllOrganizer();
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Organizer>> getOrganizerById(@PathVariable Integer id){
		return organizerService.getOrganizerById(id);
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<Organizer>> updateOrganzer(@PathVariable Integer id,@RequestBody Organizer org){
		return organizerService.updateOrganizer(id,org);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteOrganizer(@PathVariable Integer id) {
        return organizerService.deleteOrganizerById(id);
    }
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Organizer>>> getOrganizerByPaginationAndSorting(@PathVariable Integer pageNumber,@PathVariable Integer pageSize,@PathVariable String field){
		return organizerService.getOrganizerByPaginationAndSorting(pageNumber,pageSize,field);
	}
}
