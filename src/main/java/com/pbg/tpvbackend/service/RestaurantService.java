package com.pbg.tpvbackend.service;

import com.pbg.tpvbackend.dto.restaurant.RestaurantDto;
import com.pbg.tpvbackend.dto.restaurant.RestaurantPostDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantAlreadyExists;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;

public interface RestaurantService {

	public RestaurantDto create(RestaurantPostDto restaurantPostDto) throws UserNotFoundException, RestaurantAlreadyExists, UserWithoutRestaurantChain;
	
}
