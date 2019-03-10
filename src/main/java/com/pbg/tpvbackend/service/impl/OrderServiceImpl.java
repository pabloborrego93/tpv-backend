package com.pbg.tpvbackend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.pbg.tpvbackend.dao.order.OrderDao;
import com.pbg.tpvbackend.dao.order.OrderLineDao;
import com.pbg.tpvbackend.dto.order.OrderDto;
import com.pbg.tpvbackend.dto.order.OrderPostDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.order.OrderNotFoundException;
import com.pbg.tpvbackend.exception.product.ProductNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.exception.user.UserDoesntWorkInRestaurantException;
import com.pbg.tpvbackend.exception.zone.ZoneNotFoundException;
import com.pbg.tpvbackend.mapper.OrderMapper;
import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.order.Order;
import com.pbg.tpvbackend.model.order.OrderLine;
import com.pbg.tpvbackend.model.order.OrderStatus;
import com.pbg.tpvbackend.model.product.Product;
import com.pbg.tpvbackend.service.OrderService;
import com.pbg.tpvbackend.service.ProductService;
import com.pbg.tpvbackend.service.RestaurantService;
import com.pbg.tpvbackend.service.ZoneService;
import com.pbg.tpvbackend.service.security.UserDataService;

import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	OrderDao orderDao;
	OrderLineDao orderLineDao;
	OrderMapper orderMapper;
	UserDataService userDataService;
	RestaurantService restaurantService;
	ZoneService zoneService;
	ProductService productService;
	
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
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public OrderDto newOrder(Integer idZone, List<OrderPostDto> orderPostDto) throws ZoneNotFoundException, ProductNotFoundException {
		Order newOrder = new Order();
		newOrder.setCreatedAt(new Date());
		newOrder.setStatus(OrderStatus.OPENED);
		newOrder.setZone(zoneService.findById(idZone));
		newOrder = orderDao.save(newOrder);
		for(OrderPostDto orderLineDto: orderPostDto) {
			Product product = productService.findById(orderLineDto.getId());
			OrderLine orderLine = new OrderLine(newOrder, product);
			orderLine.setAmount(orderLineDto.getAmount());
			orderLine.calculateTotal();
			orderLineDao.save(orderLine);
		}
		return orderMapper.asOrderDto(newOrder);
	}

	public Order findById(Integer id) throws OrderNotFoundException {
		Optional<Order> order = orderDao.findById(id);
		if(order.isPresent()) {
			return order.get();
		} else {
			throw new OrderNotFoundException();
		}
	}
	
	@Override
	public OrderDto closeOrder(Integer idOrder) throws OrderNotFoundException {
		Order order = this.findById(idOrder);
		order.setClosedAt(new Date());
		order.setStatus(OrderStatus.CLOSED);
		Double total = order.getOrderLines().stream().mapToDouble((o) -> o.getTotal()).sum();
		order.setTotal(total);
		order = orderDao.save(order);
		return orderMapper.asOrderDto(order);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public OrderDto updateOrder(Integer idOrder, @Valid List<OrderPostDto> orderPostDto) throws ProductNotFoundException, OrderNotFoundException {
		Order order = this.findById(idOrder);
		for(OrderPostDto orderLineDto: orderPostDto) {
			Product product = productService.findById(orderLineDto.getId());
			OrderLine orderLine = new OrderLine(order, product);
			orderLine.setAmount(orderLineDto.getAmount());
			orderLine.calculateTotal();
			orderLineDao.save(orderLine);
		}
		return orderMapper.asOrderDto(order);
	}
	
}
