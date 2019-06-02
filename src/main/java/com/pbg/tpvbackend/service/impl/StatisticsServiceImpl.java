package com.pbg.tpvbackend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pbg.tpvbackend.dto.statistics.StatisticsTotalsDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.service.OrderService;
import com.pbg.tpvbackend.service.ProductFamilyService;
import com.pbg.tpvbackend.service.ProductService;
import com.pbg.tpvbackend.service.RestaurantService;
import com.pbg.tpvbackend.service.StatisticsService;

import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

	ProductService productService;
	RestaurantService restaurantService;
	ProductFamilyService productFamilyService;
	OrderService orderService;
	
	public StatisticsTotalsDto totalProducts() throws UserNotFoundException {
		return new StatisticsTotalsDto("Productos", productService.countByChain().doubleValue());
	}

	@Override
	public StatisticsTotalsDto totalRestaurants() throws UserNotFoundException {
		return new StatisticsTotalsDto("Restaurantes", restaurantService.countByChain().doubleValue());
	}

	@Override
	public StatisticsTotalsDto totalProductFamilies() throws UserNotFoundException {
		return new StatisticsTotalsDto("Familia Productos", productFamilyService.countByChain().doubleValue());
	}

	@Override
	public StatisticsTotalsDto totalWorkers() throws UserNotFoundException {
		return new StatisticsTotalsDto("Trabajadorees", 6.00);
	}

	@Override
	public List<StatisticsTotalsDto> totalEarningsByRestaurantChainByDay() {
		return orderService.totalEarningsByRestaurantChainByDay();
	}

	@Override
	public List<StatisticsTotalsDto> totalOrdersByRestaurantChainByDay() {
		return orderService.totalOrdersByRestaurantChainByDay();
	}
	
}
