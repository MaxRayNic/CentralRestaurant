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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.stackroute.domain.Restaurant;
import com.stackroute.repository.RestaurantRepository;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceImplTests {

	private RestaurantServiceImpl restaurantServiceImpl;
	@Mock
	private RestaurantRepository restaurantRepository;
	@Mock
	private Restaurant restaurant;
	@Mock
	private List<Restaurant> restaurants;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		restaurantServiceImpl = new RestaurantServiceImpl();
		restaurantServiceImpl.setRestaurantRepository(restaurantRepository);
	}

	@Test
	public void shouldReturnRestaurant_whenSearchByIdIsCalled() throws Exception {
		when(restaurantRepository.findById(2)).thenReturn(restaurant);
		// Act
		Restaurant retrievedRestaurant = restaurantServiceImpl.searchById(2);
		// Assert
		assertThat(retrievedRestaurant, is(equalTo(restaurant)));
	}

	@Test
	public void shouldReturnRestaurantList_whenFindAllIsCalled() throws Exception {
		when(restaurantRepository.findAll()).thenReturn(restaurants);
		// Act
		List<Restaurant> retrievedRestaurants = restaurantServiceImpl.findAll();
		// Assert
		assertThat(retrievedRestaurants, is(equalTo(restaurants)));
	}

	@Test
	public void shouldReturnRestaurant_whenSearchByNameIsCalled() throws Exception {
		when(restaurantRepository.findByRestaurantName("Truffles")).thenReturn(restaurant);
		// Act
		Restaurant retrievedRestaurant = restaurantServiceImpl.searchByRestaurantName("Truffles");
		// Assert
		assertThat(retrievedRestaurant, is(equalTo(restaurant)));
	}

	@Test
	public void shouldReturnRestaurant_whenaddRestaurantIsCalled() throws Exception {
		// Arrange
		when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
		// Act
		Restaurant savedRestaurant = restaurantServiceImpl.addRestaurant(restaurant);
		// Assert
		assertThat(savedRestaurant, is(equalTo(restaurant)));
	}

	@Test
	public void shouldCallDeleteMethodOfRestaurantRepository_whenDeleteRestaurantIsCalled() throws Exception {
		// Arrange
		doNothing().when(restaurantRepository).deleteById(2);
		RestaurantRepository my = Mockito.mock(RestaurantRepository.class);
		// Act
		restaurantServiceImpl.deleteRestaurant(2);
		// Assert
		verify(restaurantRepository, times(1)).deleteById(2);
	}

}