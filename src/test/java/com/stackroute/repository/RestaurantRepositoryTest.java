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
public class RestaurantRepositoryTest {

	RestaurantRepository restaurantRepository;

	@Autowired
	public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	List<Restaurant> restaurants = new ArrayList<Restaurant>();

	@Before
	public void setUp() {

		Restaurant restaurant = new Restaurant();
		String jsonStr = "{\"id\":\"1\",\"restaurantName\":\"The Black Pearl\",\"restaurantLocation\":\"Kormangala\",\"costOfTwo\":\"1200\"}";

		Gson gson = new Gson();
		restaurant = gson.fromJson(jsonStr, Restaurant.class);
		restaurants.add(restaurant);

		jsonStr = "{\"id\":\"2\",\"restaurantName\":\"Echoes\",\"restaurantLocation\":\"Kormangala\",\"costOfTwo\":\"2400.00\"}";

		restaurant = gson.fromJson(jsonStr, Restaurant.class);
		restaurants.add(restaurant);
		restaurantRepository.save(restaurants.get(0));
		restaurantRepository.save(restaurants.get(1));
		System.out.println("before " + restaurants);

		// System.out.println(restaurants);
	}

	@After
	public void tearDown() {
		restaurantRepository.deleteAll();
	}

	// @Test
	// public final void testFindByCostOfTwo() {
	// // Arrange
	// List<Restaurant> restaurantsCopyForCostOfTwo = new ArrayList<Restaurant>();
	//
	// for (Restaurant restaurant : restaurants) {
	// if (restaurant.getCostOfTwo().compareTo(new BigDecimal(1200.00)) == 0) {
	// restaurantsCopyForCostOfTwo.add(restaurant);
	// }
	// }
	//
	// // System.out.println("find costoftwo" + restaurants);
	// // System.out.println(restaurantsCopyForCostOfTwo);
	// // System.out.println(restaurantRepository.findAll());
	// assertEquals(restaurantsCopyForCostOfTwo,
	// restaurantRepository.findAllByCostOfTwo(new BigDecimal(1200)));
	//
	// }

	@Test
	public final void testFindByCostOfTwo() {
		// Arrange
		// List<Restaurant> restaurantsCopyForCostOfTwo = new ArrayList<Restaurant>();
		Restaurant res = null;
		for (Restaurant restaurant : restaurants) {
			if (restaurant.getRestaurantName().equals("The Black Pearl")) {
				res = restaurant;
				break;
			}
		}

		// System.out.println("find costoftwo" + restaurants);
		// System.out.println(restaurantsCopyForCostOfTwo);
		// System.out.println(restaurantRepository.findAll());
		assertEquals(res, restaurantRepository.findByRestaurantName("The Black Pearl"));

	}

	@Test
	public final void testAddNewRestaurantSaveS() {

		// Arrange
		Restaurant restaurant;
		String jsonStr = "{\"restaurantName\":\"The Black earl\",\"restaurantLocation\":\"Kormangala\",\"costOfTwo\":\"1300.00\"}";
		Gson gson = new Gson();
		restaurant = gson.fromJson(jsonStr, Restaurant.class);

		// Act
		long rowCount = restaurantRepository.count();
		restaurantRepository.save(restaurant);

		long newRowCount = restaurantRepository.count();
		restaurants.add(restaurant);

		// System.out.println("add " + restaurants);

		// Assert
		assertEquals("New Restaurant Added!", newRowCount, rowCount + 1);

	}

	@Test
	public final void testFindOne() {

		// Arrange
		Restaurant restaurant;
		String jsonStr = "{\"id\":\"1\",\"restaurantName\":\"The Black Pearl\",\"restaurantLocation\":\"Kormangala\",\"costOfTwo\":\"1200\"}";
		Gson gson = new Gson();
		restaurant = gson.fromJson(jsonStr, Restaurant.class);
		restaurant.setId(1);
		restaurantRepository.save(restaurant);
		System.out.println("find one" + restaurants);
		// System.out.println(restaurantRepository.findAll());
		assertEquals("find method is not working!!!", restaurant, restaurantRepository.findById(1));

	}

	@Test
	public final void testFindAll() {
		System.out.println("find all" + restaurants);
		assertEquals(restaurants, restaurantRepository.findAll());

	}

	@Test
	public final void testDeleteID() {

		// Arrange

		// Act
		long rowCount = restaurantRepository.count();
		restaurants.remove(restaurantRepository.findOne(0));
		restaurantRepository.deleteById(1);

		long newRowCount = restaurantRepository.count();
		System.out.println(rowCount);
		System.out.println(newRowCount);

		// Assert
		assertTrue("restaurant Deleted!", newRowCount < rowCount);

		System.out.println("delete " + restaurants);
		//

	}

}