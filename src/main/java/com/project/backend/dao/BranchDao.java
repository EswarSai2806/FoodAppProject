package com.project.backend.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.backend.model.Admin;
import com.project.backend.model.Branch;
import com.project.backend.repository.BranchRepository;

import java.util.*;
@Repository
public class BranchDao {

	@Autowired
	private BranchRepository repository;

	public Branch saveBranch(Branch branch) {
		return repository.save(branch);
	}

	public Branch updateBranch(Branch branch, int id) {
		if (repository.findById(id).isEmpty()) {
			return null;
		} else {
			branch.setId(id);
			Admin admin = getBranchById(id).get().getAdmin();
			branch.setAdmin(admin);
			return repository.save(branch);
		}
	}

	public Optional<Branch> getBranchById(int id) {
		return repository.findById(id);
	}

	public Branch deleteBranch(int id) {
		Branch branch = getBranchById(id).get();
		repository.delete(branch);
		return branch;
	}

	public List<Branch> findAllBranch() {
		return repository.findAll();
	}
}
