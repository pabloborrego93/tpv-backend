package com.pbg.tpvbackend.service;

import org.springframework.data.domain.Page;

import com.pbg.tpvbackend.dto.order.OrderDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.exception.user.UserDoesntWorkInRestaurantException;

public interface OrderService {

	Page<OrderDto> findByRestaurantPaged(Integer idRestaurant, Integer page, Integer max_per_page) throws RestaurantNotFoundException, UserNotFoundException, UserDoesntWorkInRestaurantException;
	
}
