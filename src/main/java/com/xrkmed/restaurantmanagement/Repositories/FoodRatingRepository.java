package com.xrkmed.restaurantmanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xrkmed.restaurantmanagement.Model.FoodRating;

@Repository
public interface FoodRatingRepository extends JpaRepository<FoodRating, Long> {
}
