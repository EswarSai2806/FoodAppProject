package com.project.backend.dao;

import java.util.*;
import com.project.backend.model.Staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.backend.model.FoodOrder;
import com.project.backend.repository.FoodOrderRepository;
@Repository
public class FoodOrderDao {
	@Autowired
	private FoodOrderRepository repository;

	public FoodOrder saveFoodOrder(FoodOrder foodOrder) {
		return repository.save(foodOrder);
	}

	public Optional<FoodOrder> getFoodOrderById(int id) {
		return repository.findById(id);
	}

	public FoodOrder updateFoodOrder(FoodOrder foodOrder, int id) {
		if (repository.findById(id).isEmpty()) {
			return null;
		} else {
			foodOrder.setId(id);
			Staff staff = getFoodOrderById(id).get().getStaff();
			foodOrder.setStaff(staff);
			return repository.save(foodOrder);
		}
	}

	public FoodOrder deleteFoodOrder(int id) {
		FoodOrder foodOrder = getFoodOrderById(id).get();
		repository.delete(foodOrder);
		return foodOrder;
	}

	public List<FoodOrder> findAllFoodOrder() {
		return repository.findAll();
	}
}
