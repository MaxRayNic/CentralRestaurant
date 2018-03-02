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
import com.stackroute.services.RestaurantServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
	Restaurant restaurant1, restaurant2;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private RestaurantServiceImpl restaurantServiceImpl;

	@Autowired
	ObjectMapper objectMapper;

	@Before
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
	public void testFindAll() throws Exception {

		List<Restaurant> allRestaurants = Arrays.asList(restaurant1, restaurant2);

		given(restaurantServiceImpl.findAll()).willReturn(allRestaurants);

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
	public void testAddRestaurant() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		this.mvc.perform(post("/api/v1/restaurant").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(restaurant1)))
				.andExpect(status().isCreated()).andExpect(status().is(201));
	}

	@Test
	public void testSearchById() throws Exception {

		given(restaurantServiceImpl.searchById(1)).willReturn(restaurant1);

		mvc.perform(get("/api/v1/restaurant/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isFound()).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.restaurantName", is("Truffles")))
				.andExpect(jsonPath("$.restaurantLocation", is("Bglr"))).andExpect(jsonPath("$.costOfTwo", is(2000)));

	}

	@Test
	public void testSearchByName() throws Exception {

		given(restaurantServiceImpl.searchByRestaurantName("Truffles")).willReturn(restaurant1);

		mvc.perform(get("/api/v1/restaurant?name=Truffles"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.restaurantName", is("Truffles")))
				.andExpect(jsonPath("$.restaurantLocation", is("Bglr"))).andExpect(jsonPath("$.costOfTwo", is(2000)));

	}

	@Test
	public void testDelete() throws Exception {

		given(restaurantServiceImpl.deleteRestaurant(1)).willReturn("Restaurant deleted");
		MvcResult result = this.mvc.perform(delete("/api/v1/restaurant/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8")).andReturn();

		String content = result.getResponse().getContentAsString();

		assertEquals("Restaurant deleted", content);

	}

}
