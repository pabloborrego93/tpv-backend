package com.pbg.tpvbackend.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pbg.tpvbackend.dao.kitchen.KitchenDao;
import com.pbg.tpvbackend.dto.kitchen.KitchenProductDto;
import com.pbg.tpvbackend.exception.kitchen.KitchenProductNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.mapper.KitchenMapper;
import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.kitchen.KitchenProduct;
import com.pbg.tpvbackend.model.kitchen.KitchenProductStatus;
import com.pbg.tpvbackend.service.KitchenService;
import com.pbg.tpvbackend.service.RestaurantService;
import com.pbg.tpvbackend.service.security.UserDataService;

import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class KitchenServiceImpl implements KitchenService {

	KitchenDao kitchenDao;
	KitchenMapper kitchenMapper;
	UserDataService userDataService;
	RestaurantService restaurantService;

	@Override
	public KitchenProductDto create(KitchenProduct kP) {
		kP = kitchenDao.save(kP);
		return kitchenMapper.asDto(kP);
	}

	@Override
	public List<KitchenProductDto> findByRestaurant(Integer idRestaurant) throws RestaurantNotFoundException {
		Restaurant restaurant = restaurantService.findById(idRestaurant);
		List<KitchenProduct> kitchenProducts = kitchenDao.findByRestaurant(restaurant);
		return kitchenProducts.stream().map(kP -> kitchenMapper.asDto(kP)).collect(Collectors.toList());
	}

	@Override
	public KitchenProductDto kitchenProductNextState(Integer idKitchenProduct) throws KitchenProductNotFoundException {
		KitchenProduct kitchenProduct = this.findById(idKitchenProduct);
		kitchenProduct.setStatus(this.next(kitchenProduct.getStatus()));
		kitchenProduct = kitchenDao.save(kitchenProduct);
		return kitchenMapper.asDto(kitchenProduct);
	}

	@Override
	public KitchenProduct findById(Integer idKitchenProduct) throws KitchenProductNotFoundException {
		Optional<KitchenProduct> kitchenProduct = kitchenDao.findById(idKitchenProduct);
		if(kitchenProduct.isPresent()) {
			return kitchenProduct.get();
		} else {
			throw new KitchenProductNotFoundException();
		}
	}
	
	private KitchenProductStatus next(KitchenProductStatus currentStatus) {
		if(currentStatus.equals(KitchenProductStatus.QUEUED)) {
			return KitchenProductStatus.PREPARING;
		} else if(currentStatus.equals(KitchenProductStatus.PREPARING)) {
			return KitchenProductStatus.PREPARED;
		} else {
			return KitchenProductStatus.DONE;
		}
	}
	
}
