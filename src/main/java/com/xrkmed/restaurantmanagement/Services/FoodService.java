package com.xrkmed.restaurantmanagement.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrkmed.restaurantmanagement.Model.Food;
import com.xrkmed.restaurantmanagement.Repositories.FoodRepository;

@Service
public class FoodService {
	
	@Autowired
	FoodRepository repository;
	
	public Food findById(Long id) {
		var food = repository.findById(id).get();
		return food;
	}

}
