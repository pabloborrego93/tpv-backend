package com.pbg.tpvbackend.service;

import java.util.List;

import com.pbg.tpvbackend.dto.navigation.NavigationDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.UserWithoutRestaurantChain;

public interface NavigationService {

	public List<NavigationDto> getNavigation() throws UserNotFoundException;
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN() throws UserNotFoundException, UserWithoutRestaurantChain;
	public NavigationDto getNavigation_ROLE_RESTAURANT_ADMIN();
	
}
