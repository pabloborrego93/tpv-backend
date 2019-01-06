package com.pbg.tpvbackend.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.order.OrderDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/order")
public class OrderController {

	OrderService orderService;
	
	@GetMapping("/{idRestaurant}")
	public Page<OrderDto> search(
		@PathVariable("idRestaurant") Integer idRestaurant, 
		@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, 
		@RequestParam(value = "max_per_page", required = false, defaultValue = "10") Integer max_per_page) throws UserNotFoundException, RestaurantNotFoundException {
		return orderService.findByRestaurantPaged(idRestaurant, page, max_per_page);
	}
	
}
