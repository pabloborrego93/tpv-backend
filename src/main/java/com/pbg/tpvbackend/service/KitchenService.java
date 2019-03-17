package com.pbg.tpvbackend.service;

import java.util.List;

import com.pbg.tpvbackend.dto.kitchen.KitchenProductDto;
import com.pbg.tpvbackend.exception.kitchen.KitchenProductNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.model.kitchen.KitchenProduct;

public interface KitchenService {

	KitchenProductDto create(KitchenProduct kP);

	List<KitchenProductDto> findByRestaurant(Integer idRestaurant) throws RestaurantNotFoundException;

	KitchenProductDto kitchenProductNextState(Integer idKitchenProduct) throws KitchenProductNotFoundException;
	
	KitchenProduct findById(Integer idKitchenProduct) throws KitchenProductNotFoundException;
	
}
