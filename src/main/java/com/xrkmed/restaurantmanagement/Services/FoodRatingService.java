package com.xrkmed.restaurantmanagement.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrkmed.restaurantmanagement.DTO.FoodRatingDTO;
import com.xrkmed.restaurantmanagement.Mapper.DozerMapper;
import com.xrkmed.restaurantmanagement.Model.Food;
import com.xrkmed.restaurantmanagement.Model.FoodRating;
import com.xrkmed.restaurantmanagement.Repositories.FoodRatingRepository;

@Service
public class FoodRatingService {
	
	@Autowired
	FoodRatingRepository repository;
	
	public List<FoodRatingDTO> findAll(){
		var foodList = repository.findAll();
		return DozerMapper.parseObjectsList(foodList, FoodRatingDTO.class);
	}
	
	public List<FoodRatingDTO> findAllByFoodId(Long foodId){
		List<FoodRating> foodsList = repository.findAll().stream().filter(x -> x.getFood().getId() == foodId).toList();
		
		return DozerMapper.parseObjectsList(foodsList, FoodRatingDTO.class);
	}
	
	public FoodRatingDTO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> {
			throw new RuntimeException(String.format("Nao foi possivel encontrar a avaliacao %s", id));
		});
		
		return DozerMapper.parseObject(entity, FoodRatingDTO.class);
	}
	
	public FoodRatingDTO create(FoodRatingDTO entity) {
		var foodRating = DozerMapper.parseObject(entity, FoodRating.class);
		return DozerMapper.parseObject(repository.save(foodRating), FoodRatingDTO.class);
	}
	
	public FoodRatingDTO update(FoodRatingDTO entity) {
		var food = repository
				.findById(entity.getKey())
				.orElseThrow(() -> {
					throw new RuntimeException(String.format("Nao foi possivel encontrar a avaliacao %s", entity.getKey()));
					}
				);
		
		food.setAuthor(entity.getAuthor());
		food.setComment(entity.getComment());
		food.setFood(DozerMapper.parseObject(food, FoodRating.class).getFood());
		
		return DozerMapper.parseObject(repository.save(food), FoodRatingDTO.class);
	}
	
	public void delete(Long id) {
		var entity = repository
				.findById(id)
				.orElseThrow(() -> {
					throw new RuntimeException(String.format("Nao foi possivel encontrar a avaliacao %s", id));
					}
				);
		
		
		repository.delete(entity);
	}

}
