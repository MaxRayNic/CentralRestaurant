package com.stackroute.exception;

import com.stackroute.domain.Restaurant;
//import com.sun.mail.handlers.message_rfc822;

public class RestaurantAlreadyExistsException extends RuntimeException {
	

	
	public RestaurantAlreadyExistsException(Restaurant res){
		super(res.getRestaurantName() +" is already present");
		
		
		
	}

}

