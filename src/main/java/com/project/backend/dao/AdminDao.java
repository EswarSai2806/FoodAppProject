package com.project.backend.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.backend.model.Admin;
import com.project.backend.repository.AdminRepository;
@Repository
public class AdminDao {
	
	@Autowired
	private AdminRepository repository;
	
	public Admin saveAdmin(Admin admin) {
		return repository.save(admin);
	}
	public Admin updateAdmin(Admin admin,int id) {
		if(repository.findById(id).isEmpty()) {
			return null;
		}
		else {
			admin.setId(id);
			return repository.save(admin);
		}
	}
	public Optional<Admin> getAdminById(int id){
		return repository.findById(id);
	}
	public Admin deleteAdmin(int id) {
		Admin admin = getAdminById(id).get();
		repository.delete(admin);
		return admin;
	}
	public List<Admin> findAllAdmin(){
		return repository.findAll();
	}
}
