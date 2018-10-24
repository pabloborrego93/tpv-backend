package com.pbg.tpvbackend.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pbg.tpvbackend.dao.RestaurantDao;
import com.pbg.tpvbackend.dto.restaurant.RestaurantDto;
import com.pbg.tpvbackend.dto.restaurant.RestaurantPostDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantAlreadyExists;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.mapper.RestaurantMapper;
import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.service.RestaurantChainService;
import com.pbg.tpvbackend.service.RestaurantService;
import com.pbg.tpvbackend.service.security.UserDataService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
	
	UserDataService userDataService;
	RestaurantChainService restaurantChainService;
	RestaurantMapper restaurantMapper;
	RestaurantDao restaurantDao;
	
	@Override
	public RestaurantDto create(RestaurantPostDto restaurantPostDto) throws UserNotFoundException, RestaurantAlreadyExists, UserWithoutRestaurantChain {
		RestaurantChain chain = restaurantChainService.findChainByUser();
		Optional<Restaurant> restaurant = chain
			.getRestaurants()
			.stream()
			.filter(rest -> rest.getName().toUpperCase().equals(restaurantPostDto.getName().toUpperCase()))
			.findFirst();
		if(restaurant.isPresent()) {
			throw new RestaurantAlreadyExists(restaurant.get().getName());
		}
		Restaurant newRestaurant = restaurantMapper.restaurantPostDtoAsRestaurant(restaurantPostDto);
		newRestaurant.setRestaurantChain(chain);
		newRestaurant = restaurantDao.save(newRestaurant);
		chain.getRestaurants().add(newRestaurant);
		restaurantChainService.update(chain);
		return restaurantMapper.asRestaurantDto(newRestaurant);
	}

	@Override
	public RestaurantDto findOne(String name) throws UserNotFoundException, UserWithoutRestaurantChain, RestaurantNotFoundException {
		RestaurantChain chain = restaurantChainService.findChainByUser();
		
		Optional<Restaurant> restaurant = chain
			.getRestaurants()
			.stream()
			.filter(rest -> rest.getName().toUpperCase().equals(name.toUpperCase()))
			.findFirst();
		
		if(!restaurant.isPresent()) {
			throw new RestaurantNotFoundException(name);
		}
		
		return restaurantMapper.asRestaurantDto(restaurant.get());
	}

}
