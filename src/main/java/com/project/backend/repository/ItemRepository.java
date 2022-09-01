package com.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.backend.model.Item;

public interface ItemRepository extends JpaRepository<Item,Integer>{

}
