package com.stackroute.domain;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Restaurant class with the setters and getters of all field names.
 * 
 */
@Document
@CompoundIndex(def = "{'restaurantName':1, 'restaurantLocation':1}",unique=true,name = "compound_index" )
public class Restaurant {

	/**
	 * Identity number for a restaurant
	 */
	private int id;

	/**
	 * Name of the restaurant
	 */
	private String restaurantName;

	/**
	 * Location of the restaurant like Koramangal, etc
	 */
	private String restaurantLocation;

	/**
	 * The standard value of the Cost of Two for that restaurant
	 */
	private BigDecimal costOfTwo;

	/**
	 * Default empty constructor
	 */
	// Restaurant() {
	//
	// }

	/**
	 * getter for restaurant name
	 * 
	 * @return
	 */
	public String getRestaurantName() {
		return restaurantName;
	}

	/**
	 * getter for the identity number
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * setter for identity number
	 * 
	 * @param id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * setter for the name of the restaurant
	 * 
	 * @param restaurantName
	 */
	public void setRestaurantName(final String restaurantName) {
		this.restaurantName = restaurantName;
	}

	/**
	 * getter for the location of the restaurant
	 * 
	 * @return
	 */
	public String getRestaurantLocation() {
		return restaurantLocation;
	}

	/**
	 * setter for the location of the restaurant
	 * 
	 * @param restaurantLocation
	 */
	public void setRestaurantLocation(final String restaurantLocation) {
		this.restaurantLocation = restaurantLocation;
	}

	/**
	 * getter for the standard value of cost of two
	 * 
	 * @return
	 */
	public BigDecimal getCostOfTwo() {
		return costOfTwo;
	}

	/**
	 * setter for the cost of two
	 * 
	 * @param costOfTwo
	 */
	public void setCostOfTwo(final BigDecimal costOfTwo) {
		this.costOfTwo = costOfTwo;
	}

	@Override
	/**
	 * returning a complete list of entities value.
	 */
	public String toString() {
		return "Restaurant [id=" + id + ", restaurantName=" + restaurantName + ", restaurantLocation="
				+ restaurantLocation + ", costOfTwo=" + costOfTwo + "]";
	}

	@Override
	/**
	 * overriding the object hashCode for comparing multiple Restaurant objects
	 */
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((costOfTwo == null) ? 0 : costOfTwo.hashCode());
		result = prime * result + id;
		result = prime * result + ((restaurantLocation == null) ? 0 : restaurantLocation.hashCode());
		result = prime * result + ((restaurantName == null) ? 0 : restaurantName.hashCode());
		return result;
	}

	@Override
	/**
	 * Overriding the Object class equalTo method in order to compare objects on
	 * the basis of fields
	 */
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Restaurant other = (Restaurant) obj;
		if (costOfTwo == null) {
			if (other.costOfTwo != null) {
				return false;
			}
		} else if (!costOfTwo.equals(other.costOfTwo)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (restaurantLocation == null) {
			if (other.restaurantLocation != null) {
				return false;
			}
		} else if (!restaurantLocation.equals(other.restaurantLocation)) {
			return false;
		}
		if (restaurantName == null) {
			if (other.restaurantName != null) {
				return false;
			}
		} else if (!restaurantName.equals(other.restaurantName)) {
			return false;
		}
		return true;
	}
}
