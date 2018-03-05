package com.stackroute.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Restaurant;
import com.stackroute.services.RestaurantService;

//Test for Controller.
@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
/**
 * RestaurantControllerTest is the jUnit Test class for testing the working of
 * RestaurantController class
 * 
 * @author
 *
 */
public class RestaurantControllerTest {

	/**
	 * restaurant object to use it in the test case validation
	 */
	private Restaurant restaurant1;

	/**
	 * restaurant object to use it in the test case validation
	 */
	private Restaurant restaurant2;

	@Autowired
	/**
	 * An object of MockMvc to perform the mocking
	 */
	private MockMvc mvc;

	@MockBean
	/**
	 * Creating a MockBean of RestaurantService interface
	 */
	private RestaurantService restaurantService;

	@Autowired
	/**
	 * Creating an object of ObjectMapper
	 */
	private ObjectMapper objectMapper;

	@Before
	/**
	 * setUp() will execute before every test case and will create two
	 * restaurant objects
	 */
	public void setUp() {
		restaurant1 = new Restaurant();
		restaurant1.setId(1);
		restaurant1.setRestaurantLocation("Bglr");
		restaurant1.setCostOfTwo(new BigDecimal(2000));
		restaurant1.setRestaurantName("Truffles");

		restaurant2 = new Restaurant();
		restaurant2.setId(2);
		restaurant2.setRestaurantLocation("Bglr1");
		restaurant2.setCostOfTwo(new BigDecimal(20002));
		restaurant2.setRestaurantName("Truffles1");
	}

	@Test
	/**
	 * this method will execute unit test for findAll() method
	 * 
	 * @throws Exception
	 */
	public void testFindAll() throws Exception {
		// Arrange
		final List<Restaurant> allRestaurants = Arrays.asList(restaurant1, restaurant2);

		given(restaurantService.findAll()).willReturn(allRestaurants);
		// Act
		// mvc class set the content type and compare with the expected result.
		mvc.perform(get("/api/v1/restaurant").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].restaurantName", is("Truffles")))
				.andExpect(jsonPath("$[0].restaurantLocation", is("Bglr")))
				.andExpect(jsonPath("$[0].costOfTwo", is(2000))).andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].restaurantName", is("Truffles1")))
				.andExpect(jsonPath("$[1].restaurantLocation", is("Bglr1")))
				.andExpect(jsonPath("$[1].costOfTwo", is(20002)));
	}

	@Test
	/**
	 * this method will test the addRestaurant() method
	 * 
	 * @throws Exception
	 */
	public void testAddRestaurant() throws Exception {
		// ObjectMapper class is used to convert json to string.
		final ObjectMapper mapper = new ObjectMapper();
		// Act
		this.mvc.perform(post("/api/v1/restaurant").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(restaurant1)))
				.andExpect(status().isCreated()).andExpect(status().is(201));
	}

	@Test
	/**
	 * this method will test searchById() method
	 * 
	 * @throws Exception
	 */
	public void testSearchById() throws Exception {
		// Arrange
		given(restaurantService.searchById(1)).willReturn(restaurant1);
		// Act
		mvc.perform(get("/api/v1/restaurant/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isFound()).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.restaurantName", is("Truffles")))
				.andExpect(jsonPath("$.restaurantLocation", is("Bglr"))).andExpect(jsonPath("$.costOfTwo", is(2000)));

	}

	@Test
	/**
	 * this method will test findByRestaurantMethod
	 * 
	 * @throws Exception
	 */
	public void testSearchByName() throws Exception {
		// Arrange
		given(restaurantService.searchByRestaurantName("Truffles")).willReturn(restaurant1);
		// Act
		mvc.perform(get("/api/v1/restaurant/restaurantname?name=Truffles"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.restaurantName", is("Truffles")))
				.andExpect(jsonPath("$.restaurantLocation", is("Bglr"))).andExpect(jsonPath("$.costOfTwo", is(2000)));

	}

	@Test
	/**
	 * this method will test deleteRestaurant method
	 * 
	 * @throws Exception
	 */
	public void testDelete() throws Exception {
		// Arrange

		given(restaurantService.deleteRestaurant(1)).willReturn("Restaurant deleted");
		// Act
		final MvcResult result = this.mvc.perform(delete("/api/v1/restaurant/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8")).andReturn();

		final String content = result.getResponse().getContentAsString();
		// Assert
		assertEquals("Restaurant deleted", content);

	}

}
