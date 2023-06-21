package com.xrkmed.restaurantmanagement.DTO;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder(value = {"id", "name", "price", "description", "picture_url"})
public class FoodDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "id")
	@Mapping("id")
	private long key;
	private String name;
	private String description;
	@JsonProperty(value = "picture_url")
	private String pictureUrl;
	private double price;

	public FoodDTO() {
	}
	
	public FoodDTO(Long key, String name, String description, double price, String pictureUrl) {
		this.key = key;
		this.name = name;
		this.description = description;
		this.price = price;
		this.pictureUrl = pictureUrl;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
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

	@Override
	public int hashCode() {
		return Objects.hash(description, key, name, pictureUrl, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodDTO other = (FoodDTO) obj;
		return Objects.equals(description, other.description) && key == other.key && Objects.equals(name, other.name)
				&& Objects.equals(pictureUrl, other.pictureUrl)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}

	
	
}
