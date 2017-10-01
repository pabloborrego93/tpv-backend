package com.pbg.tpvBackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvBackEnd.dto.RestaurantChainDto;
import com.pbg.tpvBackEnd.dto.RestaurantChainPostDto;
import com.pbg.tpvBackEnd.service.RestaurantChainService;

@RestController
@RequestMapping(value = "/restaurantChain")
public class RestaurantChainController {

	@Autowired
	RestaurantChainService restaurantChainService;
	
	@RequestMapping(method = RequestMethod.POST)
	public RestaurantChainDto create(@RequestBody RestaurantChainPostDto restaurantChainPostDto) {
		return restaurantChainService.create(restaurantChainPostDto);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<RestaurantChainDto> findByName(
			@RequestParam(required=true) String name,
			@RequestParam(required=false, defaultValue = "0") Integer page,
			@RequestParam(required=false, defaultValue = "10") Integer max) {
		return restaurantChainService.findByName(name, page, max);
	}
	
}
