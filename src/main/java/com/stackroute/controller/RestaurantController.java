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
import com.stackroute.services.RestaurantServiceImpl;

@RestController
@RequestMapping(value = "/api/v1")
public class RestaurantController {

	RestaurantService restaurantService;

	@Autowired
	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@PostMapping(value = "/restaurant")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {

		Restaurant addedRestaurant = restaurantService.addRestaurant(restaurant);

		return new ResponseEntity<Restaurant>(addedRestaurant, HttpStatus.CREATED);

	}

	@DeleteMapping(value = "/restaurant/{restaurantId}")
	public ResponseEntity<String> deleteRestaurant(@PathVariable("restaurantId") int restaurantId) {

		String deletedMessage = restaurantService.deleteRestaurant(restaurantId);

		return new ResponseEntity<String>(deletedMessage, HttpStatus.OK);

	}

	@GetMapping(value = "/restaurant/{id}")
	public ResponseEntity<Restaurant> searchById(@PathVariable("id") int restaurantId) {
		Restaurant restaurant = restaurantService.searchById(restaurantId);
		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.FOUND);

	}

	@RequestMapping(value = "/restaurant")
	public ResponseEntity<List<Restaurant>> findAllRestaurant() {
		List<Restaurant> allRestaurants = restaurantService.findAll();
		return new ResponseEntity<List<Restaurant>>(allRestaurants, HttpStatus.OK);
	}

	// @RequestMapping(value = "/restaurant/costOfTwo/{costOfTwo}")
	// public ResponseEntity<List<Restaurant>> findByCostOfTwo(@PathVariable
	// BigDecimal costOfTwo) {
	// List<Restaurant> allRestaurants =
	// restaurantService.findByCostOfTwo(costOfTwo);
	// return new ResponseEntity<List<Restaurant>>(allRestaurants, HttpStatus.OK);
	// }

	@RequestMapping(value = "/restaurant", params = "name")
	public ResponseEntity<Restaurant> findByRestaurantName(@RequestParam("name") String restaurantName) {
		Restaurant restaurantByName = ((RestaurantServiceImpl) restaurantService)
				.searchByRestaurantName(restaurantName);
		return new ResponseEntity<Restaurant>(restaurantByName, HttpStatus.OK);
	}

}
