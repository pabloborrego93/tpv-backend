package com.pbg.tpvbackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.statistics.StatisticsTotalsDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.service.StatisticsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/statistics")
@AllArgsConstructor
public class StatisticsController {

	private StatisticsService statisticsService;
	
	@GetMapping("/products")
	public ResponseEntity<StatisticsTotalsDto> totalProducts() throws UserNotFoundException {
		return ResponseEntity.ok(statisticsService.totalProducts());
	}
	
	@GetMapping("/productFamilies")
	public ResponseEntity<StatisticsTotalsDto> totalProductFamilies() throws UserNotFoundException {
		return ResponseEntity.ok(statisticsService.totalProductFamilies());
	}
	
	@GetMapping("/restaurants")
	public ResponseEntity<StatisticsTotalsDto> totalRestaurants() throws UserNotFoundException {
		return ResponseEntity.ok(statisticsService.totalRestaurants());
	}
	
	@GetMapping("/workers")
	public ResponseEntity<StatisticsTotalsDto> totalWorkers() throws UserNotFoundException {
		return ResponseEntity.ok(statisticsService.totalWorkers());
	}
	
	@GetMapping("/earnings")
	public ResponseEntity<List<StatisticsTotalsDto>> totalEarnings() {
		return ResponseEntity.ok(statisticsService.totalEarningsByRestaurantChainByDay());
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<StatisticsTotalsDto>> totalOrders() {
		return ResponseEntity.ok(statisticsService.totalOrdersByRestaurantChainByDay());
	}
	
}
