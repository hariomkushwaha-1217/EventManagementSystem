package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.eventmanagement.entity.Registration;
import com.eventmanagement.repository.RegistrationRepository;

@Repository
public class RegistrationDao {
	@Autowired
	private RegistrationRepository regisRepository;
	
	public Registration createRegistration(Registration reg) {
		return regisRepository.save(reg);
	}
	public List<Registration> getAllRegistration(){
		return regisRepository.findAll();
	}
	public Optional<Registration> getRegistrationById(Integer id){
		return regisRepository.findById(id);
	}
	public void cancelRegistrationById(Integer id) {
	    regisRepository.deleteById(id);
	}
	public List<Registration> getRegistrationByEventId(Integer id){
		return regisRepository.getRegistrationByEventId(id);
	}
	public List<Registration> getRegistrationByAttendee(String name){
		return regisRepository.getRegistrationByAttendee(name);
	}
	 public Page<Registration> getRegistrationByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	        return regisRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
	    }
}
