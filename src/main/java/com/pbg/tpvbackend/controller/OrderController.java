package com.pbg.tpvbackend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.order.OrderDto;
import com.pbg.tpvbackend.dto.order.OrderPostDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.order.OrderNotFoundException;
import com.pbg.tpvbackend.exception.product.ProductNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.exception.user.UserDoesntWorkInRestaurantException;
import com.pbg.tpvbackend.exception.zone.ZoneNotFoundException;
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
		@RequestParam(value = "max_per_page", required = false, defaultValue = "10") Integer max_per_page) throws UserNotFoundException, RestaurantNotFoundException, UserDoesntWorkInRestaurantException {
		return orderService.findByRestaurantPaged(idRestaurant, page, max_per_page);
	}
	
	@PostMapping("/create/{idZone}")
	public OrderDto create(
		@PathVariable("idZone") Integer idZone, 
		@RequestBody @Valid List<OrderPostDto> orderPostDto) throws ZoneNotFoundException, ProductNotFoundException {
		return orderService.newOrder(idZone, orderPostDto);
	}
	
	@PostMapping("/update/{idOrder}")
	public OrderDto update(
		@PathVariable("idOrder") Integer idOrder, 
		@RequestBody @Valid List<OrderPostDto> orderPostDto) throws ZoneNotFoundException, ProductNotFoundException, OrderNotFoundException {
		return orderService.updateOrder(idOrder, orderPostDto);
	}
	
	@GetMapping("/close/{idOrder}")
	public OrderDto close(@PathVariable("idOrder") Integer idOrder) throws OrderNotFoundException {
		return orderService.closeOrder(idOrder);
	}
	
}
