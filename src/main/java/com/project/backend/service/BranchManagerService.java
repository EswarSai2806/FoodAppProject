package com.project.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.backend.dao.BranchDao;
import com.project.backend.dao.BranchManagerDao;
import com.project.backend.exception.IdNotFoundException;
import com.project.backend.model.Branch;
import com.project.backend.model.BranchManager;
import com.project.backend.util.AES;
import com.project.backend.util.ResponseStructure;

@Service
public class BranchManagerService {
	@Autowired
	private BranchManagerDao dao;

	@Autowired
	private AES aes;

	@Autowired
	private BranchDao branchDao;

	@SuppressWarnings("static-access")
	public ResponseEntity<ResponseStructure<BranchManager>> saveBranchManager(BranchManager branchManager,
			int branchId) {
		Branch branch = branchDao.getBranchById(branchId).get();
		branchManager.setBranch(branch);
		branchManager.setPassword(aes.encrypt(branchManager.getPassword(), "password"));
		ResponseStructure<BranchManager> structure = new ResponseStructure<BranchManager>();
		structure.setMessage("Branch Manager Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveBranchManager(branchManager));
		return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<BranchManager>> getBranchManagerById(int id) {
		BranchManager branchManager = dao.getBranchManagerById(id).get();
		if (branchManager == null) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<BranchManager> structure = new ResponseStructure<BranchManager>();
			structure.setMessage("Branch Manager Found Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(branchManager);
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.OK);
		}
	}

	@SuppressWarnings("static-access")
	public ResponseEntity<ResponseStructure<BranchManager>> updateBranchManager(BranchManager branchManager, int id) {
		ResponseStructure<BranchManager> structure = new ResponseStructure<BranchManager>();
		BranchManager branchManager2 = dao.updateBranchManager(branchManager, id);
		if (branchManager2 != null) {
			branchManager2.setPassword(aes.encrypt(branchManager.getPassword(), "password"));
			structure.setMessage("Admin Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.saveBranchManager(branchManager2));
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("ID is not valid");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<BranchManager>> deleteBranchManager(int id) {
		ResponseStructure<BranchManager> structure = new ResponseStructure<BranchManager>();
		BranchManager branchManager = dao.getBranchManagerById(id).get();
		if (branchManager != null) {
			structure.setMessage("Branch Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.deleteBranchManager(id));
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<BranchManager>>> findAllBranchManager() {
		ResponseStructure<List<BranchManager>> structure = new ResponseStructure<List<BranchManager>>();
		structure.setMessage("Branch Found Successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllBranchManager());
		return new ResponseEntity<ResponseStructure<List<BranchManager>>>(structure, HttpStatus.OK);
	}
}
