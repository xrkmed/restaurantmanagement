package com.xrkmed.restaurantmanagement.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_ratings")
public class FoodRating implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "rating")
	private float rating;
	
	@Column(name = "date")
	private Date timestamp;
	
	@OneToOne
	@JoinColumn(name = "food_id")
	private Food food;
		
	public FoodRating(long id, String author, String comment, float rating, Date timestamp, Food food) {
		this.id = id;
		this.author = author;
		this.comment = comment;
		this.rating = rating;
		this.timestamp = timestamp;
		this.food = food;
	}

	public FoodRating() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
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
		return Objects.hash(author, comment, food, id, rating, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodRating other = (FoodRating) obj;
		return Objects.equals(author, other.author) && Objects.equals(comment, other.comment)
				&& Objects.equals(food, other.food) && id == other.id
				&& Float.floatToIntBits(rating) == Float.floatToIntBits(other.rating)
				&& Objects.equals(timestamp, other.timestamp);
	}




	
	
	

}
