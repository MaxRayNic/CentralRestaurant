package com.stackroute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.domain.Restaurant;
import com.stackroute.services.RestaurantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * controller class with five methods-
 * 
 */
@RestController
@RequestMapping(value = "/api/v1")
@Api(value = "Restaurant", description = "Add/Search/Delete Restaurants")
/**
 * 
 * RestaurantController class should call the addRestaurant,deleteResataurant,
 * searchById,findByRestaurant,findAll methods of the RestaurantService
 * interface and return the objects to the client
 * 
 * @author
 *
 */
public class RestaurantController {

	/**
	 * An object of RestaurantService interface declared to be used as a
	 * reference for its implemented class
	 */
	RestaurantService restaurantService;

	@Autowired
	/**
	 * setter for the restaurantService type object (either
	 * RestaurantServiceImpl or RestaurantMemoryServiceImpl
	 * 
	 * @param restaurantService
	 */
	public void setRestaurantService(final RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@ApiOperation(value = "Add restaurant to your Favorites list", response = ResponseEntity.class)
	@PostMapping(value = "/restaurant", produces = "application/json")
	/**
	 * This addRestaurant method is calling another addRestaurant method from
	 * RestaurantService to add the restaurant object in the database
	 * 
	 * @param restaurant
	 * @return Restaurant
	 */
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody final Restaurant restaurant) {

		final Restaurant addedRestaurant = restaurantService.addRestaurant(restaurant);

		return new ResponseEntity<Restaurant>(addedRestaurant, HttpStatus.CREATED);

	}

	@ApiOperation(value = "Delete restaurant from your Favorites list", response = ResponseEntity.class)
	@DeleteMapping(value = "/restaurant/{restaurantId}", produces = "text/plain")
	/**
	 * deleteRestaurant method is called from restaurantService interface used
	 * with delete mapping
	 * 
	 * @param restaurantId
	 * @return String
	 */
	public ResponseEntity<String> deleteRestaurant(@PathVariable("restaurantId") final int restaurantId) {

		final String deletedMessage = restaurantService.deleteRestaurant(restaurantId);

		return new ResponseEntity<String>(deletedMessage, HttpStatus.OK);

	}

	@ApiOperation(value = "Serach restaurant by providing restaurant id", response = ResponseEntity.class)
	@GetMapping(value = "/restaurant/{id}", produces = "application/json")
	/**
	 * searchById method is called from restaurantService Interface and get
	 * mapping is used
	 * 
	 * @param restaurantId
	 * @return Restaurant
	 */
	public ResponseEntity<Restaurant> searchById(@PathVariable("id") final int restaurantId) {
		final Restaurant restaurant = restaurantService.searchById(restaurantId);
		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.FOUND);

	}

	@ApiOperation(value = "List all the available restaurants", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(value = "/restaurant", produces = "application/json")
	/**
	 * findAll method is called from restaurantService Interface and request
	 * mapping is used
	 * 
	 * @return List<Restaurant>
	 */
	public ResponseEntity<List<Restaurant>> findAllRestaurant() {
		final List<Restaurant> allRestaurants = restaurantService.findAll();
		return new ResponseEntity<List<Restaurant>>(allRestaurants, HttpStatus.OK);
	}

	@ApiOperation(value = "Find restaurant by name", response = ResponseEntity.class)
	@GetMapping(value = "/restaurant/restaurantname", params = "name", produces = "application/json")
	/**
	 * searchByRestaurantName method is called from restaurantService Interface
	 * and request mapping is used and get mapping is used
	 * 
	 * @param restaurantName
	 * @return Restaurant
	 */
	public ResponseEntity<Restaurant> searchByRestaurantName(@RequestParam("name") final String restaurantName) {
		final Restaurant restaurantByName = restaurantService.searchByRestaurantName(restaurantName);
		return new ResponseEntity<Restaurant>(restaurantByName, HttpStatus.OK);
	}

}
