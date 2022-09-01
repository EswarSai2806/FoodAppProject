package com.project.backend.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.backend.model.Branch;
import com.project.backend.model.BranchManager;
import com.project.backend.repository.BranchManagerRepository;

@Repository
public class BranchManagerDao {

	@Autowired
	BranchManagerRepository repository;

	public BranchManager saveBranchManager(BranchManager branchManager) {
		return repository.save(branchManager);
	}

	public Optional<BranchManager> getBranchManagerById(int id) {
		return repository.findById(id);
	}

	public BranchManager updateBranchManager(BranchManager branchManager, int id) {
		if (repository.findById(id).isEmpty()) {
			return null;
		} else {
			branchManager.setId(id);
			Branch branch = getBranchManagerById(id).get().getBranch();
			branchManager.setBranch(branch);
			return repository.save(branchManager);
		}
	}

	public BranchManager deleteBranchManager(int id) {
		BranchManager branchManager = getBranchManagerById(id).get();
		repository.delete(branchManager);
		return branchManager;
	}

	public List<BranchManager> findAllBranchManager() {
		return repository.findAll();
	}
}
