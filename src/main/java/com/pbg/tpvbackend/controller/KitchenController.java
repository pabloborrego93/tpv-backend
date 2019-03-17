package com.pbg.tpvbackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.kitchen.KitchenProductDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.kitchen.KitchenProductNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.exception.user.UserDoesntWorkInRestaurantException;
import com.pbg.tpvbackend.service.KitchenService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/kitchen")
public class KitchenController {

	KitchenService kitchenService;
	
	@GetMapping("/{idRestaurant}")
	public List<KitchenProductDto> search(@PathVariable("idRestaurant") Integer idRestaurant) throws UserNotFoundException, RestaurantNotFoundException, UserDoesntWorkInRestaurantException {
		return kitchenService.findByRestaurant(idRestaurant);
	}
	
	@GetMapping("/next/{idKitchenProduct}")
	public KitchenProductDto nextState(@PathVariable("idKitchenProduct") Integer idKitchenProduct) throws KitchenProductNotFoundException {
		return kitchenService.kitchenProductNextState(idKitchenProduct);
	}
	
}
