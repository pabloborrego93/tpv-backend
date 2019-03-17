package com.pbg.tpvbackend.dao.kitchen;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pbg.tpvbackend.model.kitchen.KitchenProduct;

public interface KitchenDao extends PagingAndSortingRepository<KitchenProduct, Integer> {
	
}
