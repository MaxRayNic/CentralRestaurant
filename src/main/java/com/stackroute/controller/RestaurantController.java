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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping(value = "/api/v1")
@Api(value = "Restaurant", description = "Add/Search/Delete Restaurants" )
public class RestaurantController {

	RestaurantService restaurantService;

	@Autowired
	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@ApiOperation(value = "Add restaurant to your Favorites list", response = ResponseEntity.class)
	@PostMapping(value = "/restaurant", produces = "application/json")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {

		Restaurant addedRestaurant = restaurantService.addRestaurant(restaurant);

		return new ResponseEntity<Restaurant>(addedRestaurant, HttpStatus.CREATED);

	}

	@ApiOperation(value = "Delete restaurant from your Favorites list", response = ResponseEntity.class)
	@DeleteMapping(value = "/restaurant/{restaurantId}", produces = "text/plain")
	public ResponseEntity<String> deleteRestaurant(@PathVariable("restaurantId") int restaurantId) {

		String deletedMessage = restaurantService.deleteRestaurant(restaurantId);

		return new ResponseEntity<String>(deletedMessage, HttpStatus.OK);

	}

	@ApiOperation(value = "Serach restaurant by providing restaurant id", response = ResponseEntity.class)
	@GetMapping(value = "/restaurant/{id}", produces = "application/json")
	public ResponseEntity<Restaurant> searchById(@PathVariable("id") int restaurantId) {
		Restaurant restaurant = restaurantService.searchById(restaurantId);
		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.FOUND);

	}

	@ApiOperation(value = "List all the available restaurants", response = ResponseEntity.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
	)
	@GetMapping(value = "/restaurant", produces = "application/json")
	public ResponseEntity<List<Restaurant>> findAllRestaurant() {
		List<Restaurant> allRestaurants = restaurantService.findAll();
		return new ResponseEntity<List<Restaurant>>(allRestaurants, HttpStatus.OK);
	}

	@ApiOperation(value = "Find restaurant by name", response = ResponseEntity.class)
	@GetMapping(value = "/restaurant/restaurantname", params = "name", produces = "application/json")
	public ResponseEntity<Restaurant> searchByRestaurantName(@RequestParam("name") String restaurantName) {
		Restaurant restaurantByName = ((RestaurantServiceImpl) restaurantService)
				.searchByRestaurantName(restaurantName);
		return new ResponseEntity<Restaurant>(restaurantByName, HttpStatus.OK);
	}

}
