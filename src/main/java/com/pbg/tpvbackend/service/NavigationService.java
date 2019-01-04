package com.pbg.tpvbackend.service;

import java.util.List;

import com.pbg.tpvbackend.dto.navigation.NavigationDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.chain.ChainWithoutProductFamiliesException;
import com.pbg.tpvbackend.exception.chain.ChainWithoutProductsException;
import com.pbg.tpvbackend.exception.chain.ChainWithoutUsersException;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurants;

public interface NavigationService {

	public List<NavigationDto> getNavigation() throws UserNotFoundException;
	
	/*
	 * ROLE_RESTAURANT_CHAIN_ADMIN
	 */
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_HEADER();
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN() throws UserNotFoundException, UserWithoutRestaurantChain;
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN_NOT_CREATED();
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN_RESTAURANTS() throws UserNotFoundException, UserWithoutRestaurants;
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN_RESTAURANTS_NOT_CREATED();
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_PRODUCT_FAMILIES() throws UserNotFoundException, ChainWithoutProductFamiliesException;
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_PRODUCT_FAMILIES_NOT_CREATED();
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_PRODUCTS() throws UserNotFoundException, ChainWithoutProductsException;
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_PRODUCTS_NOT_CREATED();
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_USERS() throws UserNotFoundException, ChainWithoutUsersException;
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_USERS_NOT_CREATED() throws UserNotFoundException;
	/*
	 * FIN ROLE_RESTAURANT_CHAIN_ADMIN
	 */
	
	/*
	 * ROLE_WAITER
	 */
	public NavigationDto getNavigation_ROLE_WAITER_HEADER() throws UserNotFoundException;
	/*
	 * FIN ROLE_WAITER
	 */
	
	/*
	 * ROLE_ORDER_SCREEN
	 */
	public NavigationDto getNavigation_ROLE_ORDER_SCREEN_HEADER() throws UserNotFoundException;
	/*
	 * FIN ROLE_ORDER_SCREEN
	 */
	
}
