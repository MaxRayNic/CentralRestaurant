package com.stackroute.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.domain.Restaurant;

@Repository
/**
 * Extending Mongo Repository and declaring three custom methods.
 * 
 * @author
 *
 */
public interface RestaurantRepository extends MongoRepository<Restaurant, Integer> {

	/**
	 * deleteById method will be self implemented using Springboot Application
	 * and will delete restaurant object using id
	 * 
	 * @param restaurantId
	 */
	void deleteById(int restaurantId);

	/**
	 * findById will be self implemented using Springboot Application and will
	 * find restaurant object using restaurant id
	 * 
	 * @param restaurantID
	 * @return Restaurant
	 */
	Restaurant findById(int restaurantID);

	/**
	 * findByRestaurantName method will also be self implemented using
	 * Springboot Application and will return Restaurant object based on the
	 * name.
	 * 
	 * @param restaurantName
	 * @return Restaurant
	 */
	Restaurant findByRestaurantName(String restaurantName);

}
