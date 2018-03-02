package com.stackroute.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stackroute.domain.Restaurant;

@Service
public class RestaurantMemoryServiceImpl {

	List<Restaurant> restaurants = new ArrayList<>();

	public Restaurant addrestaurant(Restaurant restaurant) {
		restaurants.add(restaurant);
		return restaurant;
	}

	public String deleteRestaurant(int restaurantId) {
		// restaurantRepostory.delete(restaurantId);
		return "restaurant deleted";

	}

	public Restaurant searchById(int restaurantID) {

		// Restaurant restaurant = restaurantRepostory.findOne(restaurantID);
		// return restaurant;
		return null;
	}

	public List<Restaurant> findAll() {

		return this.restaurants;

	}

	// @Override
	// public List<Restaurant> findByCostOfTwo(BigDecimal costOfTwo) {
	// // TODO Auto-generated method stub
	// return null;
	// }
}
