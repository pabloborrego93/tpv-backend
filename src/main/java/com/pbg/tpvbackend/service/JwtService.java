package com.pbg.tpvbackend.service;

import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;

public interface JwtService {

	public String jwt(RestaurantChainDto chainDto) throws UserNotFoundException;
	
}
