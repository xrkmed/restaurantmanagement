package com.xrkmed.restaurantmanagement.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrkmed.restaurantmanagement.DTO.FoodDTO;
import com.xrkmed.restaurantmanagement.Mapper.DozerMapper;
import com.xrkmed.restaurantmanagement.Model.Food;
import com.xrkmed.restaurantmanagement.Repositories.FoodRepository;

@Service
public class FoodService {
	
	@Autowired
	FoodRepository repository;
	
	public FoodDTO findById(Long id) {
		var food = repository.findById(id).orElseThrow(() -> new RuntimeException("Nao foi encontrado nenhuma comida com este id."));
		return DozerMapper.parseObject(food, FoodDTO.class);
	}
	
	public List<FoodDTO> findAll(){
		var foodsList =  DozerMapper.parseObjectsList(repository.findAll(), FoodDTO.class);
		
		return foodsList;
	}
	
	public FoodDTO createOne(FoodDTO foodDTO) {
		var food = DozerMapper.parseObject(foodDTO, Food.class);
		var food_save = repository.save(food);
		return DozerMapper.parseObject(food_save, FoodDTO.class);
	}
	
	public FoodDTO update(FoodDTO food) {
		var foodObj = repository.findById(food.getKey()).orElseThrow(() -> new RuntimeException("Nao foi encontrado nenhuma comida com este id."));
		foodObj.setName(food.getName());
		foodObj.setDescription(food.getDescription());
		foodObj.setPrice(food.getPrice());
		foodObj.setPictureUrl(food.getPictureUrl());
		
		return DozerMapper.parseObject(repository.save(foodObj), FoodDTO.class);
	}
	
	public void delete(Long id) {
		Food food = repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Nao foi possivel encontrar a food id %s", id)));
		repository.delete(food);
	}

}
