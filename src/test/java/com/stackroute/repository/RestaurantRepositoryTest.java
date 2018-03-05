package com.stackroute.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.stackroute.configuration.RepositoryConfiguration;
import com.stackroute.domain.Restaurant;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { RepositoryConfiguration.class })
/**
 * RestaurantRepositoryTest is a jUnit test class which is testing for the
 * working of the RestaurantRepository methods
 * 
 * @author
 *
 */
public class RestaurantRepositoryTest {

	/**
	 * creating an object of RestaurantRepository
	 */
	private RestaurantRepository restaurantRepository;

	/**
	 * creating a list of restaurant objects to use it in the testing purpose
	 */
	final List<Restaurant> restaurants = new ArrayList<Restaurant>();

	@Autowired
	/**
	 * autowired setter for the restaurantRepository object
	 * 
	 * @param restaurantRepository
	 */
	public void setRestaurantRepository(final RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	@Before
	/**
	 * setUp method will execute once before very test case execution
	 */
	public void setUp() {

		Restaurant restaurant = new Restaurant();
		String jsonStr = "{\"id\":\"1\",\"restaurantName\":\"The Black Pearl\",\"restaurantLocation\":\"Kormangala\",\"costOfTwo\":\"1200\"}";

		final Gson gson = new Gson();
		restaurant = gson.fromJson(jsonStr, Restaurant.class);
		restaurants.add(restaurant);

		jsonStr = "{\"id\":\"2\",\"restaurantName\":\"Echoes\",\"restaurantLocation\":\"Kormangala\",\"costOfTwo\":\"2400.00\"}";

		restaurant = gson.fromJson(jsonStr, Restaurant.class);
		restaurants.add(restaurant);
		restaurantRepository.save(restaurants.get(0));
		restaurantRepository.save(restaurants.get(1));

	}

	@After
	/**
	 * tearDown will be executed after every test case execution and will clean
	 * the repository
	 */
	public void tearDown() {
		restaurantRepository.deleteAll();
	}

	@Test
	/**
	 * testing method for findByRestaurantName method
	 */
	public final void shouldReturnRestaurantUsingfindByRestaurantNameIsCalled() {
		// Arrange
		Restaurant res = null;
		for (final Restaurant restaurant : restaurants) {
			if (restaurant.getRestaurantName().equals("The Black Pearl") == true) {
				res = restaurant;
				break;
			}
		}
		// Assert
		assertEquals(res, restaurantRepository.findByRestaurantName("The Black Pearl"));

	}

	@Test
	/**
	 * testing method for addRestaurant method
	 */
	public final void testAddNewRestaurantSaveS() {

		// Arrange
		Restaurant restaurant;
		String jsonStr = "{\"restaurantName\":\"The Black earl\",\"restaurantLocation\":\"Kormangala\",\"costOfTwo\":\"1300.00\"}";
		final Gson gson = new Gson();
		restaurant = gson.fromJson(jsonStr, Restaurant.class);

		// Act
		final long rowCount = restaurantRepository.count();
		restaurantRepository.save(restaurant);

		final long newRowCount = restaurantRepository.count();
		restaurants.add(restaurant);

		// Assert
		assertEquals("New Restaurant Added!", newRowCount, rowCount + 1);

	}

	@Test
	/**
	 * testing method for findOne() method
	 */
	public final void testFindOne() {

		// Arrange
		Restaurant restaurant;
		String jsonStr = "{\"id\":\"1\",\"restaurantName\":\"The Black Pearl\",\"restaurantLocation\":\"Kormangala\",\"costOfTwo\":\"1200\"}";
		Gson gson = new Gson();
		restaurant = gson.fromJson(jsonStr, Restaurant.class);
		int passingId = 1;

		// Act
		restaurant.setId(passingId);
		restaurantRepository.save(restaurant);

		// Assert
		assertEquals("find method is not working!!!", restaurant, restaurantRepository.findById(passingId));

	}

	@Test
	/**
	 * testing method for findAll() method
	 */
	public final void testFindAll() {
		// Assert
		assertEquals(restaurants, restaurantRepository.findAll());
	}

	@Test
	/**
	 * testing method for deleteById() method
	 */
	public final void testDeleteID() {

		// Arrange

		// Act
		final long rowCount = restaurantRepository.count();
		restaurants.remove(restaurantRepository.findOne(0));
		restaurantRepository.deleteById(1);

		final long newRowCount = restaurantRepository.count();

		// Assert
		assertTrue("restaurant Deleted!", newRowCount < rowCount);
	}

}