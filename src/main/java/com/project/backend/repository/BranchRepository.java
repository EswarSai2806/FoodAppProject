package com.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.backend.model.Branch;

public interface BranchRepository extends JpaRepository<Branch,Integer>{

}
