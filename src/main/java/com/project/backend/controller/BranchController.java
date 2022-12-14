package com.project.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.model.Branch;
import com.project.backend.service.BranchService;
import com.project.backend.util.ResponseStructure;
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class BranchController {
	@Autowired
	private BranchService service;

	@PostMapping("/branch/{adminId}")
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@RequestBody Branch branch, @PathVariable int adminId) {
		return service.saveBranch(branch, adminId);
	}

	@PutMapping("/branch")
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(@RequestBody Branch branch, @RequestParam int id) {
		return service.updateBranch(branch, id);
	}

	@DeleteMapping("/branch/{branchId}")
	public ResponseEntity<ResponseStructure<Branch>> deleteBranch(@PathVariable int branchId) {
		return service.deleteBranch(branchId);
	}

	@GetMapping("/branch/{id}")
	public ResponseEntity<ResponseStructure<Branch>> getBranchById(@PathVariable int id) {
		return service.getBranchById(id);
	}

	@GetMapping("/branch")
	public ResponseEntity<ResponseStructure<List<Branch>>> findAllBranch() {
		return service.findAllBranch();
	}
}
