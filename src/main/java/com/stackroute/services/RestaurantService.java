package com.stackroute.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stackroute.domain.Restaurant;

@Service
/**
 * 
 * Restaurant Service interface will contain following methods declaration
 * addRestaruant, deleteRestaurant, searchById, findAll and
 * serachByRestaurantName
 * 
 * @author
 *
 */
public interface RestaurantService {

	/**
	 * 
	 * @param restaurant
	 * @return Retaurant
	 */
	Restaurant addRestaurant(Restaurant restaurant);

	/**
	 * 
	 * @param restaurantId
	 * @return String
	 */
	String deleteRestaurant(int restaurantId);

	/**
	 * 
	 * @param restaurantID
	 * @return Restaurant
	 */
	Restaurant searchById(int restaurantID);

	/**
	 *
	 * @return List<Restaurant>
	 */
	List<Restaurant> findAll();

	/**
	 * 
	 * @param restaurantName
	 * @return Restaurant
	 */
	Restaurant searchByRestaurantName(String restaurantName);
}
