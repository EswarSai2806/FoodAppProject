package com.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.backend.model.FoodOrder;

public interface FoodOrderRepository extends JpaRepository<FoodOrder,Integer>{

}
