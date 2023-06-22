package com.xrkmed.restaurantmanagement.Services;

import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrkmed.restaurantmanagement.Controller.FoodController;
import com.xrkmed.restaurantmanagement.Controller.FoodRatingController;
import com.xrkmed.restaurantmanagement.DTO.FoodRatingDTO;
import com.xrkmed.restaurantmanagement.Mapper.DozerMapper;
import com.xrkmed.restaurantmanagement.Model.FoodRating;
import com.xrkmed.restaurantmanagement.Repositories.FoodRatingRepository;

@Service
public class FoodRatingService {
	
	@Autowired
	FoodRatingRepository repository;
	
	public List<FoodRatingDTO> findAll(){
		var entity = DozerMapper.parseObjectsList(repository.findAll(), FoodRatingDTO.class);
		entity.stream().forEach(food -> {
			food.add(linkTo(methodOn(FoodRatingController.class).findById(food.getKey())).withSelfRel());
			food.add(linkTo(methodOn(FoodController.class).findById(food.getFood().getKey())).withSelfRel());
		});
		
		return entity;
	}
	
	public List<FoodRatingDTO> findAllByFoodId(Long foodId){
		List<FoodRating> foodsList = repository.findAll().stream().filter(x -> x.getFood().getId() == foodId).toList();
		var foodsDTOList = DozerMapper.parseObjectsList(foodsList, FoodRatingDTO.class);
		foodsDTOList.stream().forEach(food -> {
			food.add(linkTo(methodOn(FoodRatingController.class).findById(food.getKey())).withSelfRel());
			food.add(linkTo(methodOn(FoodController.class).findById(food.getFood().getKey())).withSelfRel());
		});
		
		return foodsDTOList;
	}
	
	public FoodRatingDTO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> {
			throw new RuntimeException(String.format("Nao foi possivel encontrar a avaliacao %s", id));
		});
		
		var entityDTO = DozerMapper.parseObject(entity, FoodRatingDTO.class);
		
		entityDTO.add(linkTo(methodOn(FoodRatingController.class).findById(entityDTO.getKey())).withSelfRel());
		entityDTO.add(linkTo(methodOn(FoodController.class).findById(entityDTO.getFood().getKey())).withSelfRel());
		
		return entityDTO;
	}
	
	public FoodRatingDTO create(FoodRatingDTO entity) {
		var foodRating = DozerMapper.parseObject(entity, FoodRating.class);
		var foodRatingDTO = DozerMapper.parseObject(repository.save(foodRating), FoodRatingDTO.class);
		foodRatingDTO.add(linkTo(methodOn(FoodRatingController.class).findById(foodRatingDTO.getKey())).withSelfRel());
		foodRatingDTO.add(linkTo(methodOn(FoodController.class).findById(foodRatingDTO.getFood().getKey())).withSelfRel());
		return foodRatingDTO;
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
		
		var foodRatingDTO = DozerMapper.parseObject(repository.save(food), FoodRatingDTO.class);
		foodRatingDTO.add(linkTo(methodOn(FoodRatingController.class).findById(foodRatingDTO.getKey())).withSelfRel());
		foodRatingDTO.add(linkTo(methodOn(FoodController.class).findById(foodRatingDTO.getFood().getKey())).withSelfRel());
		
		return foodRatingDTO;
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
