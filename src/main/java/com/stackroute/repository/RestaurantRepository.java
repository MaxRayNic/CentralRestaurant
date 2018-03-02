package com.stackroute.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.domain.Restaurant;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, Integer> {
	// public List<Restaurant> findAllByCostOfTwo(BigDecimal costOfTwo);

	public void deleteById(int restaurantId);

	public Restaurant findById(int restaurantID);

	public Restaurant findByRestaurantName(String restaurantName);

}
