package com.project.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.backend.dao.AdminDao;
import com.project.backend.exception.IdNotFoundException;
import com.project.backend.model.Admin;
import com.project.backend.util.AES;
import com.project.backend.util.ResponseStructure;
@Service
public class AdminService {
	@Autowired
	private AdminDao dao;

//	@Autowired
//	private AES aes;

	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		admin.setPassword(AES.encrypt(admin.getPassword(), "password"));
		ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
		structure.setMessage("Admin Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveAdmin(admin));
		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin, int id) {
		ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
		Admin admin2 = dao.updateAdmin(admin, id);
		if (admin2 != null) {
			admin2.setPassword(AES.encrypt(admin.getPassword(), "password"));
			structure.setMessage("Admin Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.saveAdmin(admin2));
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("ID is not valid");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Admin>> getAdminById(int id) {
		Optional<Admin> optional = dao.getAdminById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
			structure.setMessage("Admin Found Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(int id) {
		ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
		Admin admin = dao.getAdminById(id).get();
		if (admin != null) {
			structure.setMessage("Admin Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.deleteAdmin(id));
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Admin>>> findAllAdmin() {
		ResponseStructure<List<Admin>> structure = new ResponseStructure<List<Admin>>();
		structure.setMessage("Employee Found Successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllAdmin());
		return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.OK);
	}
}
