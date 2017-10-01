package com.pbg.tpvBackEnd.service;

import org.springframework.data.domain.Page;

import com.pbg.tpvBackEnd.dto.RestaurantChainDto;
import com.pbg.tpvBackEnd.dto.RestaurantChainPostDto;

public interface RestaurantChainService {

	public RestaurantChainDto create(RestaurantChainPostDto restaurantChainPostDto);
	public Page<RestaurantChainDto> findByName(String name, Integer page, Integer max);
	
}
