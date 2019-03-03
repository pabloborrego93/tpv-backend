package com.pbg.tpvbackend.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pbg.tpvbackend.dao.order.OrderDao;
import com.pbg.tpvbackend.dto.order.OrderDto;
import com.pbg.tpvbackend.dto.order.OrderPostDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.exception.user.UserDoesntWorkInRestaurantException;
import com.pbg.tpvbackend.mapper.OrderMapper;
import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.service.OrderService;
import com.pbg.tpvbackend.service.RestaurantService;
import com.pbg.tpvbackend.service.security.UserDataService;

import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	OrderDao orderDao;
	OrderMapper orderMapper;
	UserDataService userDataService;
	RestaurantService restaurantService;
	
	@Override
	public Page<OrderDto> findByRestaurantPaged(Integer idRestaurant, Integer page, Integer max_per_page) throws RestaurantNotFoundException, UserNotFoundException, UserDoesntWorkInRestaurantException {
		RestaurantChain chain = userDataService.chain();
		Optional<Restaurant> optRestaurant = chain.getRestaurants().stream().filter((r) -> r.getId().equals(idRestaurant)).findFirst();
		if(optRestaurant.isPresent()) {
			if(restaurantService.worksIn(optRestaurant.get().getId(), userDataService.getId())) {
				return orderDao.findByRestaurant(optRestaurant.get(), PageRequest.of(page, max_per_page)).map((o) -> orderMapper.asOrderDto(o));
			} else {
				throw new UserDoesntWorkInRestaurantException();
			}
		} else {
			throw new RestaurantNotFoundException();
		}
	}

	@Override
	public OrderDto newOrder(OrderPostDto orderPostDto) {
		return null;
	}
	
}
