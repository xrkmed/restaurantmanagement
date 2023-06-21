package com.xrkmed.restaurantmanagement.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_foods")
public class Food implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "picture_url")
	private String pictureUrl;
	
	@Column(name = "price")
	private double price;
	
	@OneToMany(mappedBy = "food")
	private List<FoodRating> ratings;

	public Food() {
		ratings = new ArrayList<>();
	}
	
	public Food(Long id, String name, String description, double price, String pictureUrl) {
		this();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.pictureUrl = pictureUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public List<FoodRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<FoodRating> ratings) {
		this.ratings = ratings;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, pictureUrl, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(pictureUrl, other.pictureUrl)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}
	
	
}
