package com.pbg.tpvbackend.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pbg.tpvbackend.dao.order.OrderDao;
import com.pbg.tpvbackend.dto.order.OrderDto;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.mapper.OrderMapper;
import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.service.OrderService;
import com.pbg.tpvbackend.service.security.UserDataService;

import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	OrderDao orderDao;
	OrderMapper orderMapper;
	UserDataService userDataService;
	
	@Override
	public Page<OrderDto> findByRestaurantPaged(Integer idRestaurant, Integer page, Integer max_per_page) throws RestaurantNotFoundException {
		RestaurantChain chain = userDataService.chain();
		Optional<Restaurant> optRestaurant = chain.getRestaurants().stream().filter((r) -> r.getId().equals(idRestaurant)).findFirst();
		if(optRestaurant.isPresent()) {
			return orderDao.findByRestaurant(optRestaurant.get(), PageRequest.of(page, max_per_page)).map((o) -> orderMapper.asOrderDto(o));
		} else {
			throw new RestaurantNotFoundException();
		}
	}
	
}
