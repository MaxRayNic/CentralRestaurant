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
import org.springframework.util.MultiValueMap;

import com.stackroute.RestaurantApplication;
import com.stackroute.domain.Restaurant;
import com.stackroute.exception.ExceptionResponse;

//Integration testing
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestaurantApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/**
 * RestaurantControllerIT is the integration test class
 * 
 * @author
 *
 */
public class RestaurantControllerIT {

	@LocalServerPort
	/**
	 * creating a port entity just to handle the port values
	 */
	private int port;

	/**
	 * creating a TestRestTemplate to work on the integration test
	 */
	private TestRestTemplate restTemplate = new TestRestTemplate();

	/**
	 * creating HttpHeaders object
	 */
	private HttpHeaders headers = new HttpHeaders();

	/**
	 * creating HttpHeaders object
	 */
	private HttpHeaders headers1 = new HttpHeaders();
	private HttpHeaders headers2 = new HttpHeaders();
	private HttpHeaders headers3 = new HttpHeaders();

	@Test
	/**
	 * method for testing the functionality of searchById method of
	 * RestauratController class
	 */
	public void testSearchById() {

		final Restaurant restaurant = new Restaurant();
		restaurant.setId(4);
		restaurant.setRestaurantName("Gillys1");
		restaurant.setRestaurantLocation("Bangalore1");
		restaurant.setCostOfTwo(new BigDecimal(200000));

		HttpEntity<Restaurant> entity1 = new HttpEntity<Restaurant>(restaurant, headers1);

		ResponseEntity<String> response1 = restTemplate.exchange(createURLWithPort("/api/v1/restaurant"),
				HttpMethod.POST, entity1, String.class);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers1);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/restaurant/4"),
				HttpMethod.GET, entity, String.class);

		String expected = "{id:4,restaurantName:Gillys1,restaurantLocation:Bangalore1,costOfTwo:200000}";

		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Test
	/**
	 * method for testing the functionality of addRestaurant method of
	 * RestauratController class
	 */
	public void addRestaurantTest() {

		final Restaurant restaurant = new Restaurant();
		restaurant.setId(1);
		restaurant.setRestaurantName("Truffles");
		restaurant.setRestaurantLocation("Bangalore");
		restaurant.setCostOfTwo(new BigDecimal(20000));

		// Declaring an httpEntity for Restaurant
		final HttpEntity<Restaurant> entity = new HttpEntity<Restaurant>(restaurant, headers);

		// restTemplate class is used to create a Post request.
		final ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/restaurant"),
				HttpMethod.POST, entity, String.class);

		String expected = "{id:1,restaurantName:Truffles,restaurantLocation:Bangalore,costOfTwo:20000}";

		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testSearchByName() {

		ExceptionResponse e = new ExceptionResponse();
		e.setMessage("Oc doesnot exist");
		e.setDetails("uri=/api/v1/restaurant");
		
		HttpEntity<ExceptionResponse> entity1 = new HttpEntity<ExceptionResponse>(e, headers3);

		ResponseEntity<String> response1 = restTemplate.exchange(createURLWithPort("/api/v1/restaurant"),
				HttpMethod.POST, entity1, String.class);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers1);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/restaurant?name=Oc"),
				HttpMethod.GET, entity, String.class);

		String expected = "{message: Oc doesnot exist,details: uri=/api/v1/restaurant}";

		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Test
	public void addRestaurantTestException() {
		
		Restaurant restaurant = new Restaurant();
		restaurant.setId(2);
		restaurant.setRestaurantName("A");
		restaurant.setRestaurantLocation("F");
        restaurant.setCostOfTwo(new BigDecimal(200));

		ExceptionResponse e = new ExceptionResponse();
		e.setMessage("A is already present");
		e.setDetails("uri=/api/v1/restaurant");

		//HttpEntity<ExceptionResponse> entity = new HttpEntity<ExceptionResponse>(e, headers2);
		HttpEntity<Restaurant> entity1 = new HttpEntity<Restaurant>(restaurant, headers2);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/restaurant"),
				HttpMethod.POST, entity1, String.class);
		String expected = "{message: A is already present,details: uri=/api/v1/restaurant}";

		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}


	/**
	 * Method generates the url where the application is running.
	 * 
	 * @param uri
	 * @return String
	 */
	private String createURLWithPort(final String uri) {
		return "http://localhost:" + port + uri;
	}
}