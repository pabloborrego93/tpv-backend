package com.pbg.tpvbackend.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.itextpdf.text.DocumentException;
import com.pbg.tpvbackend.dto.order.OrderDto;
import com.pbg.tpvbackend.dto.order.OrderPostDto;
import com.pbg.tpvbackend.dto.statistics.StatisticsTotalsDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.gcp.AccessTokenException;
import com.pbg.tpvbackend.exception.gcp.SubmitJobException;
import com.pbg.tpvbackend.exception.order.OrderNotFoundException;
import com.pbg.tpvbackend.exception.printer.PrinterNotFoundException;
import com.pbg.tpvbackend.exception.product.ProductNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.exception.user.UserDoesntWorkInRestaurantException;
import com.pbg.tpvbackend.exception.zone.ZoneNotFoundException;
import com.pbg.tpvbackend.model.order.Order;
import com.pbg.tpvbackend.model.order.OrderStatus;

public interface OrderService {

	Page<OrderDto> findByRestaurantPaged(Integer idRestaurant, OrderStatus orderStatus, Integer page, Integer max_per_page) throws RestaurantNotFoundException, UserNotFoundException, UserDoesntWorkInRestaurantException;
	OrderDto newOrder(Integer idZone, @Valid List<OrderPostDto> orderPostDto) throws ZoneNotFoundException, ProductNotFoundException;
	public Order findById(Integer id) throws OrderNotFoundException;
	OrderDto closeOrder(Integer idOrder) throws OrderNotFoundException;
	OrderDto updateOrder(Integer idOrder, @Valid List<OrderPostDto> orderPostDto) throws ProductNotFoundException, OrderNotFoundException;

	void printTicket(Integer idOrder) throws DocumentException, OrderNotFoundException, PrinterNotFoundException, IOException, AccessTokenException, GeneralSecurityException, SubmitJobException;
	List<StatisticsTotalsDto> totalEarningsByRestaurantChainByDay();
	List<StatisticsTotalsDto> totalOrdersByRestaurantChainByDay();
}
