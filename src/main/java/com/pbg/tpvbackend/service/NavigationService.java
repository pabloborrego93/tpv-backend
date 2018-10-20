package com.pbg.tpvbackend.service;

import java.util.List;

import com.pbg.tpvbackend.dto.navigation.NavigationDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurants;

public interface NavigationService {

	public List<NavigationDto> getNavigation() throws UserNotFoundException;
	
	/*
	 * ROLE_RESTAURANT_CHAIN_ADMIN
	 */
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN() throws UserNotFoundException, UserWithoutRestaurantChain;
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN_NOT_CREATED() throws UserNotFoundException;
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN_RESTAURANTS() throws UserNotFoundException, UserWithoutRestaurants;
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN_RESTAURANTS_NOT_CREATED() throws UserNotFoundException;
	/*
	 * FIN ROLE_RESTAURANT_CHAIN_ADMIN
	 */
	
	public NavigationDto getNavigation_ROLE_RESTAURANT_ADMIN();
	
}
