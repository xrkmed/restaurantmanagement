package com.xrkmed.restaurantmanagement.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.xrkmed.restaurantmanagement.Controller.FoodController;
import com.xrkmed.restaurantmanagement.DTO.FoodDTO;
import com.xrkmed.restaurantmanagement.Mapper.DozerMapper;
import com.xrkmed.restaurantmanagement.Model.Food;
import com.xrkmed.restaurantmanagement.Model.FoodRating;
import com.xrkmed.restaurantmanagement.Repositories.FoodRatingRepository;
import com.xrkmed.restaurantmanagement.Repositories.FoodRepository;

@Service
public class FoodRatingService {
	
	@Autowired
	FoodRatingRepository repository;
	
	public List<FoodRating> findAll(){
		return repository.findAll();
	}
	
	public List<FoodRating> findAllByFoodId(Long foodId){
		List<FoodRating> foodsList = repository.findAll().stream().filter(x -> x.getFood().getId() == foodId).toList();
		System.out.println(foodsList.size());
		
		return foodsList;
	}
	
	public FoodRating findById(Long id) {
		return repository.findById(id).orElseThrow(() -> {
			throw new RuntimeException(String.format("Nao foi possivel encontrar a avaliacao %s", id));
		});
	}
	
	public FoodRating create(FoodRating entity) {
		return repository.save(entity);
	}
	
	public FoodRating update(FoodRating entity) {
		FoodRating foodDB = repository
				.findById(entity.getId())
				.orElseThrow(() -> {
					throw new RuntimeException(String.format("Nao foi possivel encontrar a avaliacao %s", entity.getId()));
					}
				);
		
		foodDB.setAuthor(entity.getAuthor());
		foodDB.setComment(entity.getComment());
		foodDB.setFood(entity.getFood());
		
		return repository.save(foodDB);
	}
	
	public void delete(Long id) {
		FoodRating entity = repository
				.findById(id)
				.orElseThrow(() -> {
					throw new RuntimeException(String.format("Nao foi possivel encontrar a avaliacao %s", id));
					}
				);
		
		
		repository.delete(entity);
	}

}
