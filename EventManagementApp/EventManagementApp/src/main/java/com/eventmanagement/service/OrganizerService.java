package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.dao.OrganizerDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Organizer;
import com.eventmanagement.exception.NoRecordAvailableException;
import com.eventmanagement.exception.IdNotFoundException;

@Service
public class OrganizerService {
    @Autowired
    private OrganizerDao orgDao;

    public ResponseEntity<ResponseStructure<Organizer>> AddOrganizer(Organizer org) {
        if (org == null) {
            throw new IllegalArgumentException("Organizer cannot be null");
        }
        Organizer savedOrg = orgDao.addOrganizer(org);
        ResponseStructure<Organizer> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("Organizer details are saved");
        structure.setData(savedOrg);
        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<List<Organizer>>> getAllOrganizer() {
        List<Organizer> events = orgDao.getAllOrganizer();
        ResponseStructure<List<Organizer>> structure = new ResponseStructure<>();
        if (!events.isEmpty()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Organizer Records Found");
            structure.setData(events);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }

    // Fetch record by id
    public ResponseEntity<ResponseStructure<Organizer>> getOrganizerById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Organizer> org = orgDao.getOrganizerById(id);
        ResponseStructure<Organizer> structure = new ResponseStructure<>();
        if (org.isPresent()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Organizer record is fetched");
            structure.setData(org.get());
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("Record not found");
        }
    }

    // Update 
    public ResponseEntity<ResponseStructure<Organizer>> updateOrganizer(Integer id, Organizer org) {
        if (id == null || org == null) {
            throw new IllegalArgumentException("Id and Organizer cannot be null");
        }
        Optional<Organizer> existingOrganizer = orgDao.getOrganizerById(id);
        ResponseStructure<Organizer> structure = new ResponseStructure<>();
        if (existingOrganizer.isPresent()) {
        		org.setId(id);
            Organizer updatedOrg = orgDao.updateOrganizer(org);
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Organizer record updated successfully");
            structure.setData(updatedOrg);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } 
        else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }

    public ResponseEntity<ResponseStructure<String>> deleteOrganizerById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Organizer> opt = orgDao.getOrganizerById(id);
        ResponseStructure<String> structure = new ResponseStructure<>();
        if (opt.isPresent()) {
            orgDao.deleteOrganizerById(id);
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Organizer deleted successfully");
            structure.setData("Deleted Organizer with ID: " + id);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Organizer with given ID not found");
        }
    }

    public ResponseEntity<ResponseStructure<Page<Organizer>>> getOrganizerByPaginationAndSorting(Integer pageNumber, Integer pageSize, String field) {
        if (pageNumber == null || pageSize == null || field == null) {
            throw new IllegalArgumentException("Pagination parameters cannot be null");
        }
        Page<Organizer> pages = orgDao.getOrganizerByPaginationAndSorting(pageNumber, pageSize, field);
        ResponseStructure<Page<Organizer>> structure = new ResponseStructure<>();
        if (pages.hasContent()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Records retrieved by sorting");
            structure.setData(pages);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }
}