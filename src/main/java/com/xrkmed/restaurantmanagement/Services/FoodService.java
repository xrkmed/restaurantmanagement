package com.xrkmed.restaurantmanagement.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.xrkmed.restaurantmanagement.Controller.FoodController;
import com.xrkmed.restaurantmanagement.Controller.FoodRatingController;
import com.xrkmed.restaurantmanagement.DTO.FoodDTO;
import com.xrkmed.restaurantmanagement.Mapper.DozerMapper;
import com.xrkmed.restaurantmanagement.Model.Food;
import com.xrkmed.restaurantmanagement.Repositories.FoodRepository;

@Service
public class FoodService {
	
	@Autowired
	FoodRepository repository;
	
	public FoodDTO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Nao foi encontrado nenhuma comida com este id."));
		var foodDTO = DozerMapper.parseObject(entity, FoodDTO.class);
		foodDTO.add(linkTo(methodOn(FoodController.class).findById(id)).withSelfRel());
		foodDTO.add(linkTo(methodOn(FoodRatingController.class).findByFoodId(id)).withSelfRel());
		
		return foodDTO;
	}
	
	public List<FoodDTO> findAll(){
		var foodsList =  DozerMapper.parseObjectsList(repository.findAll(), FoodDTO.class);
		foodsList.stream().forEach(food -> {
			food.add(linkTo(methodOn(FoodController.class).findById(food.getKey())).withSelfRel());
			food.add(linkTo(methodOn(FoodRatingController.class).findByFoodId(food.getKey())).withSelfRel());
		});
		
		return foodsList;
	}
	
	public FoodDTO createOne(FoodDTO food) {
		var entity = repository.save(DozerMapper.parseObject(food, Food.class));
		var foodDTO = DozerMapper.parseObject(entity, FoodDTO.class);
		foodDTO.add(linkTo(methodOn(FoodController.class).findById(foodDTO.getKey())).withSelfRel());
		foodDTO.add(linkTo(methodOn(FoodRatingController.class).findByFoodId(foodDTO.getKey())).withSelfRel());
		
		return foodDTO;
	}
	
	public FoodDTO update(FoodDTO food) {
		var foodObj = repository.findById(food.getKey()).orElseThrow(() -> new RuntimeException("Nao foi encontrado nenhuma comida com este id."));
		foodObj.setName(food.getName());
		foodObj.setDescription(food.getDescription());
		foodObj.setPrice(food.getPrice());
		foodObj.setPictureUrl(food.getPictureUrl());
		
		var foodDTO = DozerMapper.parseObject(repository.save(foodObj), FoodDTO.class);
		foodDTO.add(linkTo(methodOn(FoodController.class).findById(foodDTO.getKey())).withSelfRel());
		foodDTO.add(linkTo(methodOn(FoodRatingController.class).findByFoodId(foodDTO.getKey())).withSelfRel());
		return foodDTO;
	}
	
	public void delete(Long id) {
		Food food = repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Nao foi possivel encontrar a food id %s", id)));
		repository.delete(food);
	}

}
