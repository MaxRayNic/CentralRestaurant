package com.stackroute.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.stackroute.domain.Restaurant;
import com.stackroute.exception.RestaurantAlreadyExistsException;
import com.stackroute.exception.RestaurantNotFoundException;
import com.stackroute.repository.RestaurantRepository;

@Service
/**
 * RestaurantServiceImpl class is implementing RestaurantService interface and
 * its methods are defined here.
 * 
 * @author
 *
 */
public class RestaurantServiceImpl implements RestaurantService {

	/**
	 * Defining an object of RestaurantReposistory so that the methods from
	 * MongoRepository can be used
	 */
	private RestaurantRepository restaurantRepository;

	@Autowired
	/**
	 * setter for the RestaurantRepository object created above. It has been
	 * autowired for automatically bean declaration
	 * 
	 * @param restaurantRepostory
	 */
	public void setRestaurantRepository(final RestaurantRepository restaurantRepostory) {
		this.restaurantRepository = restaurantRepostory;
	}

	/**
	 * Restaurant object is added by this method using save method from the
	 * RestaurantRepository
	 * 
	 * @return restaurant
	 */
	public Restaurant addRestaurant(final Restaurant restaurant) {
		try{
			restaurantRepository.save(restaurant);
			}catch(DuplicateKeyException de){
				throw new RestaurantAlreadyExistsException(restaurant);
			}
		return restaurant;
		
	}

	/**
	 * Restaurant object is deleted by this method using deleteById method from
	 * the RestaurantRepository
	 * 
	 * @return String
	 */
	public String deleteRestaurant(final int restaurantId) {
		restaurantRepository.deleteById(restaurantId);
		return "restaurant deleted";
	}

	/**
	 * Restaurant object is searched by this method using searchById method from
	 * the RestaurantRepository
	 * 
	 * @return restaurant
	 */
	public Restaurant searchById(final int restaurantID) {
		return restaurantRepository.findById(restaurantID);
	}

	/**
	 * List of all Restaurant objects is returned by this method using findAll
	 * method from the RestaurantRepository
	 * 
	 * @return List<restaurant>
	 */
	public List<Restaurant> findAll() {
		return (List<Restaurant>) restaurantRepository.findAll();
	}

	/**
	 * Restaurant object is searched based on the name of the restaurant passed
	 * as the argument using the findByRestarantName method from
	 * RestaurantRepository
	 * 
	 * @return restaurant
	 */
	public Restaurant searchByRestaurantName(final String restaurantName) {
		Restaurant restaurant = restaurantRepository.findByRestaurantName(restaurantName);
		if(restaurant==null){
			throw new RestaurantNotFoundException(restaurantName+" doesnot exist");
			
		}
		
		return restaurant;

	}
}
