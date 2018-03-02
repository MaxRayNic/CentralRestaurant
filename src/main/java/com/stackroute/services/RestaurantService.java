package com.stackroute.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stackroute.domain.Restaurant;

@Service
public interface RestaurantService {
	public Restaurant addRestaurant(Restaurant restaurant);

	public String deleteRestaurant(int restaurantId);

	public Restaurant searchById(int restaurantID);

	public List<Restaurant> findAll();

	// public List<Restaurant> findByCostOfTwo(BigDecimal costOfTwo);
}
