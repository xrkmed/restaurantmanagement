package com.xrkmed.restaurantmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xrkmed.restaurantmanagement.DTO.FoodDTO;
import com.xrkmed.restaurantmanagement.Model.Food;
import com.xrkmed.restaurantmanagement.Services.FoodService;

@RestController
@RequestMapping(value = "/food")
public class FoodController {

	@Autowired
	FoodService service;
		
	@GetMapping(value = {"", "/"})
	public List<FoodDTO> findAll(){
		List<FoodDTO> foodList = service.findAll();
		return foodList;
	}
	
	@GetMapping(value = "/{id}")
	public FoodDTO findById(@PathVariable(value = "id") Long id){
		FoodDTO food = service.findById(id);
		return food;
	}
	
	@PostMapping(value = "/add")
	public FoodDTO createOne(@RequestBody FoodDTO food) {
		return service.createOne(food);
	}
	
	@PutMapping(value = "/update")
	public FoodDTO updateOne(@RequestBody FoodDTO food) {
		return service.update(food);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
	}
}
