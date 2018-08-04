package com.pbg.tpvbackend.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainDto;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainPostDto;
import com.pbg.tpvbackend.exception.BadRequestException;
import com.pbg.tpvbackend.service.RestaurantChainService;
import com.pbg.tpvbackend.service.utils.PaginationUtilsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/restaurantChain")
@AllArgsConstructor
public class RestaurantChainController {

	private PaginationUtilsService paginationUtilsService;
	private RestaurantChainService restaurantChainService;
	
	@RequestMapping(method = RequestMethod.POST)
	public RestaurantChainDto create(@RequestBody RestaurantChainPostDto restaurantChainPostDto) {
		return restaurantChainService.create(restaurantChainPostDto);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<RestaurantChainDto> findByName(
		@RequestParam(required=true) String name,
		@RequestParam(required=false, defaultValue = "0") Integer page,
		@RequestParam(required=false, defaultValue = "10") Integer max_per_page) throws BadRequestException {
		
		if(!paginationUtilsService.isValidPageAndMaxPerPage(page, max_per_page) || name.length() > 32)
			throw new BadRequestException();
		
		return restaurantChainService.findByName(name, page, max_per_page);
	}
	
}
