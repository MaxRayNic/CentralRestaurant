package com.stackroute.controller;

import java.math.BigDecimal;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.RestaurantApplication;
import com.stackroute.domain.Restaurant;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestaurantApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantControllerIT {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers1 = new HttpHeaders();
	HttpHeaders headers2 = new HttpHeaders();

	@Test
	public void testSearchById() {

		Restaurant restaurant = new Restaurant();
		restaurant.setId(4);
		restaurant.setRestaurantName("Gillys1");
		restaurant.setRestaurantLocation("Bangalore1");
		restaurant.setCostOfTwo(new BigDecimal(200000));

		HttpEntity<Restaurant> entity1 = new HttpEntity<Restaurant>(restaurant, headers2);

		ResponseEntity<String> response1 = restTemplate.exchange(createURLWithPort("/api/v1/restaurant"),
				HttpMethod.POST, entity1, String.class);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers1);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/restaurant/4"),
				HttpMethod.GET, entity, String.class);

		String expected = "{id:4,restaurantName:Gillys1,restaurantLocation:Bangalore1,costOfTwo:200000}";

		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void addRestaurantTest() {

		Restaurant restaurant = new Restaurant();
		restaurant.setId(1);
		restaurant.setRestaurantName("Truffles");
		restaurant.setRestaurantLocation("Bangalore");
		restaurant.setCostOfTwo(new BigDecimal(20000));

		HttpEntity<Restaurant> entity = new HttpEntity<Restaurant>(restaurant, headers2);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/restaurant"),
				HttpMethod.POST, entity, String.class);
		String expected = "{id:1,restaurantName:Truffles,restaurantLocation:Bangalore,costOfTwo:20000}";

		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}