package com.pbg.tpvbackend.dao.kitchen;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.kitchen.KitchenProduct;

public interface KitchenDao extends PagingAndSortingRepository<KitchenProduct, Integer> {
	
	List<KitchenProduct> findByRestaurant(Restaurant restaurant);
	
}
