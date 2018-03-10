package com.pbg.tpvbackend.service;

import org.springframework.data.domain.Page;

import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainDto;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainPostDto;

public interface RestaurantChainService {

	public RestaurantChainDto create(RestaurantChainPostDto restaurantChainPostDto);
	public Page<RestaurantChainDto> findByName(String name, Integer page, Integer max);
	
}
