package com.pbg.tpvbackend.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.restaurant.RestaurantDto;
import com.pbg.tpvbackend.dto.restaurant.RestaurantPostDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantAlreadyExists;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.service.RestaurantService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class RestaurantController {

	RestaurantService restaurantService;
	
	@RequestMapping(method = RequestMethod.POST)
	public RestaurantDto create(@RequestBody RestaurantPostDto restaurantPostDto) throws UserNotFoundException, RestaurantAlreadyExists, UserWithoutRestaurantChain {
		return restaurantService.create(restaurantPostDto);
	}
	
}
