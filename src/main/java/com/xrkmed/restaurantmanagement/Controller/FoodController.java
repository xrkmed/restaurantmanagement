package com.xrkmed.restaurantmanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xrkmed.restaurantmanagement.Model.Food;
import com.xrkmed.restaurantmanagement.Services.FoodService;

@RestController
@RequestMapping(value = "/food")
public class FoodController {

	@Autowired
	FoodService service;
	
	@GetMapping(value = "/{id}")
	public Food getDefault(@PathVariable(value = "id") Long id){
		Food food = service.findById(id);
		return food;
	}
}
