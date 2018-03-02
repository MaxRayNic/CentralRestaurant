package com.stackroute.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.domain.Restaurant;
import com.stackroute.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	RestaurantRepository restaurantRepository;

	@Autowired
	public void setRestaurantRepository(RestaurantRepository restaurantRepostory) {
		this.restaurantRepository = restaurantRepostory;
	}

	public Restaurant addRestaurant(Restaurant restaurant) {
		restaurantRepository.save(restaurant);
		return restaurant;
	}

	public String deleteRestaurant(int restaurantId) {
		restaurantRepository.deleteById(restaurantId);

		return "restaurant deleted";

	}

	public Restaurant searchById(int restaurantID) {

		Restaurant restaurant = restaurantRepository.findById(restaurantID);
		return restaurant;
	}

	public List<Restaurant> findAll() {

		return (List<Restaurant>) restaurantRepository.findAll();

	}

	// @Override
	// public List<Restaurant> findByCostOfTwo(BigDecimal costOfTwo) {
	// return (List<Restaurant>) restaurantRepostory.findAllByCostOfTwo(costOfTwo);
	// }

	public Restaurant searchByRestaurantName(String restaurantName) {
		return (Restaurant) restaurantRepository.findByRestaurantName(restaurantName);

	}
}
