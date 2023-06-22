package com.xrkmed.restaurantmanagement.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xrkmed.restaurantmanagement.DTO.FoodRatingDTO;
import com.xrkmed.restaurantmanagement.Mapper.DozerMapper;
import com.xrkmed.restaurantmanagement.Model.Food;
import com.xrkmed.restaurantmanagement.Services.FoodRatingService;
import com.xrkmed.restaurantmanagement.Services.FoodService;

@RestController
@RequestMapping(value = "/ratings")
public class FoodRatingController {

	@Autowired
	FoodRatingService service;
	
	@Autowired
	FoodService foodService;
		
	@GetMapping(value = {"", "/"})
	public List<FoodRatingDTO> findAll(){
		List<FoodRatingDTO> ratings = service.findAll();
		return ratings;
	}
	
	@GetMapping(value = "/{id}")
	public FoodRatingDTO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping(value = "/foodid/{id}")
	public List<FoodRatingDTO> findByFoodId(@PathVariable(value = "id") Long id) {
		return service.findAllByFoodId(id);
	}
	
	@PostMapping(value = "/add/{foodId}")
	public FoodRatingDTO add(@PathVariable(value = "foodId") long foodId, @RequestBody FoodRatingDTO object) {
		var foodDTO = foodService.findById(foodId);
		object.setFood(foodDTO);
		object.setTimestamp(new Date());
		return service.create(object);
	}
	
	@PutMapping(value = "/update")
	public FoodRatingDTO update(@RequestBody FoodRatingDTO object) {
		return service.update(object);
	}	
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
	}
}
