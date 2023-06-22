package com.xrkmed.restaurantmanagement.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.dozermapper.core.Mapping;
import com.xrkmed.restaurantmanagement.Model.Food;

public class FoodRatingDTO extends RepresentationModel<FoodRatingDTO> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Mapping(value = "id")
	private long key;
	private String author;
	private String comment;
	private float rating;
	private Date timestamp;
	@JsonIgnore
	private FoodDTO food;
		
	public FoodRatingDTO(long key, String author, String comment, float rating, Date timestamp, FoodDTO food) {
		this.key = key;
		this.author = author;
		this.comment = comment;
		this.rating = rating;
		this.timestamp = timestamp;
		this.food = food;
	}

	public FoodRatingDTO() {
	}
	
	public long getFoodId() {
		return food.getKey();
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public FoodDTO getFood() {
		return food;
	}

	public void setFood(FoodDTO food) {
		this.food = food;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, comment, key, rating);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodRatingDTO other = (FoodRatingDTO) obj;
		return Objects.equals(author, other.author) && Objects.equals(comment, other.comment) && key == other.key
				&& Float.floatToIntBits(rating) == Float.floatToIntBits(other.rating);
	}
	
	
	
	

}
