package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.eventmanagement.entity.Organizer;
import com.eventmanagement.repository.OrganizerRepository;

@Repository
public class OrganizerDao {
    @Autowired
    private OrganizerRepository orgRepository;
    
    public Organizer addOrganizer(Organizer org) {
        return orgRepository.save(org);
    }
    
    public List<Organizer> getAllOrganizer() {
        return orgRepository.findAll();
    }
    
    public Optional<Organizer> getOrganizerById(Integer id) {
        return orgRepository.findById(id);
    }
    
    public Organizer updateOrganizer(Organizer org) {
        return orgRepository.save(org);
	}
    
    public void deleteOrganizerById(Integer id) {
        orgRepository.deleteById(id);
    }
    
    public Page<Organizer> getOrganizerByPaginationAndSorting(Integer pageNumber, Integer pageSize, String field) {
        return orgRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
    }
}