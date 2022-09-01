package com.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.backend.model.Admin;

public interface AdminRepository  extends JpaRepository<Admin,Integer>{

}
