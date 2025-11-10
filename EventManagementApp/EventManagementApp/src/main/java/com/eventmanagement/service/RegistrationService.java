package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.dao.RegistrationDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Registration;
import com.eventmanagement.exception.NoRecordAvailableException;
import com.eventmanagement.exception.IdNotFoundException;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationDao registrationDao;

    // Insert a record
    public ResponseEntity<ResponseStructure<Registration>> createRegistration(Registration reg) {
        if (reg == null) {
            throw new IllegalArgumentException("Registration cannot be null");
        }
        Registration savedReg = registrationDao.createRegistration(reg);
        ResponseStructure<Registration> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("Registration record is saved!");
        structure.setData(savedReg);
        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

    // Fetch all Registration Records
    public ResponseEntity<ResponseStructure<List<Registration>>> getAllRegistration() {
        List<Registration> ls = registrationDao.getAllRegistration();
        ResponseStructure<List<Registration>> structure = new ResponseStructure<>();
        if (!ls.isEmpty()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Registration Records Found");
            structure.setData(ls);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }

    // Fetch record by id
    public ResponseEntity<ResponseStructure<Registration>> getRegistrationById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Registration> opt = registrationDao.getRegistrationById(id);
        ResponseStructure<Registration> structure = new ResponseStructure<>();
        if (opt.isPresent()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Record found");
            structure.setData(opt.get());
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Record not found");
        }
    }

    // Delete a record by id
    public ResponseEntity<ResponseStructure<String>> cancelRegistrationById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Registration> opt = registrationDao.getRegistrationById(id);
        ResponseStructure<String> structure = new ResponseStructure<>();
        if (opt.isPresent()) {
            registrationDao.cancelRegistrationById(id);
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Registration cancelled successfully");
            structure.setData("Cancelled Registration with ID: " + id);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Registration with given ID not found");
        }
    }
    
    public ResponseEntity<ResponseStructure<Page<Registration>>> getRegistrationByPaginationAndSorting(Integer pageNumber, Integer pageSize, String field) {
        if (pageNumber == null || pageSize == null || field == null) {
            throw new IllegalArgumentException("Pagination parameters cannot be null");
        }
        Page<Registration> pages = registrationDao.getRegistrationByPaginationAndSorting(pageNumber, pageSize, field);
        ResponseStructure<Page<Registration>> structure = new ResponseStructure<>();
        if (pages.hasContent()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Registration records retrieved by sorting");
            structure.setData(pages);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available");
        }
    }

	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByEventId(Integer id) {
		if(id == null)
			throw new IllegalArgumentException("ID cannot be null");
        ResponseStructure<List<Registration>> structure = new ResponseStructure<>();
        List<Registration> ls = registrationDao.getRegistrationByEventId(id);
        if(!ls.isEmpty()) {
        	structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Records found");
            structure.setData(ls);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        }
        else {
            throw new NoRecordAvailableException("No Records Available");
        }
	}

	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByAttendee(String name) {
		if(name == null)
			throw new IllegalArgumentException("ID cannot be null");
        ResponseStructure<List<Registration>> structure = new ResponseStructure<>();
        List<Registration> lr = registrationDao.getRegistrationByAttendee(name);
        if(!lr.isEmpty()) {
        	structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Records found");
            structure.setData(lr);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        }
        else {
            throw new NoRecordAvailableException("No Records Available");
        }
	}
}
