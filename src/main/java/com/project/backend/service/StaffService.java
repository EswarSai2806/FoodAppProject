package com.project.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.backend.dao.BranchManagerDao;
import com.project.backend.dao.StaffDao;
import com.project.backend.exception.IdNotFoundException;
import com.project.backend.model.BranchManager;
import com.project.backend.model.Staff;
import com.project.backend.util.AES;
import com.project.backend.util.ResponseStructure;
@Service
public class StaffService {
	@Autowired
	private StaffDao dao;

	@Autowired
	private AES aes;

	@Autowired
	private BranchManagerDao branchManagerDao;

	@SuppressWarnings("static-access")
	public ResponseEntity<ResponseStructure<Staff>> saveStaff(Staff staff, int branchManagerId) {
		BranchManager branchManager = branchManagerDao.getBranchManagerById(branchManagerId).get();
		staff.setBranchManager(branchManager);
		staff.setPassword(aes.encrypt(staff.getPassword(), "password"));
		ResponseStructure<Staff> structure = new ResponseStructure<Staff>();
		structure.setMessage("Staff Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveStaff(staff));
		return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Staff>> getStaffById(int id) {
		Staff staff = dao.getStaffById(id).get();
		if (staff == null) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<Staff> structure = new ResponseStructure<Staff>();
			structure.setMessage("Staff Found Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(staff);
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);
		}
	}

	@SuppressWarnings("static-access")
	public ResponseEntity<ResponseStructure<Staff>> updateStaff(Staff staff, int id) {
		ResponseStructure<Staff> structure = new ResponseStructure<Staff>();
		Staff staff2 = dao.updateStaff(staff, id);
		if (staff2 != null) {
			staff2.setPassword(aes.encrypt(staff.getPassword(), "password"));
			structure.setMessage("Staff Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.saveStaff(staff2));
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("ID is not valid");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Staff>> deleteStaff(int id) {
		ResponseStructure<Staff> structure = new ResponseStructure<Staff>();
		Staff staff = dao.getStaffById(id).get();
		if (staff != null) {
			structure.setMessage("Staff Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.deleteStaff(id));
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Staff>>> findAllStaff() {
		ResponseStructure<List<Staff>> structure = new ResponseStructure<List<Staff>>();
		structure.setMessage("Branch Found Successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllStaff());
		return new ResponseEntity<ResponseStructure<List<Staff>>>(structure, HttpStatus.OK);
	}
}
