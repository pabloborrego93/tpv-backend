package com.pbg.tpvbackend.service;

import java.util.List;

import com.pbg.tpvbackend.dto.statistics.StatisticsTotalsDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;

public interface StatisticsService {

	StatisticsTotalsDto totalProducts() throws UserNotFoundException;
	StatisticsTotalsDto totalRestaurants() throws UserNotFoundException;
	StatisticsTotalsDto totalProductFamilies() throws UserNotFoundException;
	StatisticsTotalsDto totalWorkers() throws UserNotFoundException;
	List<StatisticsTotalsDto> totalEarningsByRestaurantChainByDay();
	List<StatisticsTotalsDto> totalOrdersByRestaurantChainByDay();
	
}
