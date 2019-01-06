package com.pbg.tpvbackend.service;

import java.util.ArrayList;
import java.util.List;

import com.pbg.tpvbackend.dto.restaurant.RestaurantDto;
import com.pbg.tpvbackend.dto.restaurant.RestaurantPostDto;
import com.pbg.tpvbackend.dto.user.UserExtendedInfoDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantAlreadyExists;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.security.User;

public interface RestaurantService {

	public RestaurantDto create(RestaurantPostDto restaurantPostDto) throws UserNotFoundException, RestaurantAlreadyExists, UserWithoutRestaurantChain;
	public RestaurantDto findOne(String name) throws UserNotFoundException, UserWithoutRestaurantChain, RestaurantNotFoundException;
	public List<UserExtendedInfoDto> getWorkers(Integer id) throws RestaurantNotFoundException;
	public List<UserExtendedInfoDto> setWorkers(Integer id, ArrayList<UserExtendedInfoDto> workers) throws RestaurantNotFoundException;
	public Restaurant findById(Integer id);
	
	public List<Restaurant> findRestaurantsByWorkerOrderByName(User user);
	
}
