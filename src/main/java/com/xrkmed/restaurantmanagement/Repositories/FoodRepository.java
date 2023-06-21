package com.xrkmed.restaurantmanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xrkmed.restaurantmanagement.Model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

}
