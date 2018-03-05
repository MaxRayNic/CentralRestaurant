package com.stackroute.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.stackroute.domain.Restaurant;
import com.stackroute.repository.RestaurantRepository;

@RunWith(MockitoJUnitRunner.class)
/**
 * 
 * Unit test for RestaurantServiceImpl class
 *
 */
public class RestaurantServiceImplTests {

	/**
	 * Creating an object RestaurantServiceImpl
	 */
	private RestaurantServiceImpl restaurantServiceImpl;

	@Mock
	/**
	 * Creating an object RestaurantRepository
	 */
	private RestaurantRepository restaurantRepository;

	@Mock
	/**
	 * Creating an object of Restaurant
	 */
	private Restaurant restaurant;

	@Mock
	/**
	 * Creating a List of restaurant
	 */
	private List<Restaurant> restaurants;

	@Before
	/**
	 * setupMock() will be executed every time before each test
	 */
	public void setUpMock() {
		MockitoAnnotations.initMocks(this);
		restaurantServiceImpl = new RestaurantServiceImpl();
		restaurantServiceImpl.setRestaurantRepository(restaurantRepository);
	}

	@Test
	/**
	 * this method will test for the proper execution of SearchById method
	 * 
	 * @throws Exception
	 */
	public void shouldReturnRestaurantWhenSearchByIdIsCalled() throws Exception {
		// Arrange
		when(restaurantRepository.findById(2)).thenReturn(restaurant);
		// Act
		final Restaurant retrievedRestaurant = restaurantServiceImpl.searchById(2);
		// Assert
		assertThat(retrievedRestaurant, is(equalTo(restaurant)));
	}

	@Test
	/**
	 * this method will test for the proper execution of findAll method
	 * 
	 * @throws Exception
	 */
	public void shouldReturnRestaurantListWhenFindAllIsCalled() throws Exception {
		// Arrange
		when(restaurantRepository.findAll()).thenReturn(restaurants);
		// Act
		final List<Restaurant> retrievedRestaurants = restaurantServiceImpl.findAll();
		// Assert
		assertThat(retrievedRestaurants, is(equalTo(restaurants)));
	}

	@Test
	/**
	 * this method will test for the proper execution of SearchByRestaurantName
	 * 
	 * @throws Exception
	 */
	public void shouldReturnRestaurantWhenSearchByNameIsCalled() throws Exception {
		when(restaurantRepository.findByRestaurantName("Truffles")).thenReturn(restaurant);
		// Act
		final Restaurant retrievedRestaurant = restaurantServiceImpl.searchByRestaurantName("Truffles");
		// Assert
		assertThat(retrievedRestaurant, is(equalTo(restaurant)));
	}

	@Test
	/**
	 * this method will test for the proper execution of addRestuarnt
	 * 
	 * @throws Exception
	 */
	public void shouldReturnRestaurantWhenAddRestaurantIsCalled() throws Exception {
		// Arrange
		when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
		// Act
		final Restaurant savedRestaurant = restaurantServiceImpl.addRestaurant(restaurant);
		// Assert
		assertThat(savedRestaurant, is(equalTo(restaurant)));
	}

	@Test
	/**
	 * this method will test for the proper execution of deleteById
	 * 
	 * @throws Exception
	 */
	public void shouldCallDeleteMethodOfRestaurantRepositoryWhenDeleteRestaurantIsCalled() throws Exception {
		// Arrange
		doNothing().when(restaurantRepository).deleteById(2);
		// RestaurantRepository my = Mockito.mock(RestaurantRepository.class);
		// Act
		restaurantServiceImpl.deleteRestaurant(2);
		// Assert
		verify(restaurantRepository, times(1)).deleteById(2);
	}

}
