package com.pbg.tpvbackend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.Zone;

public interface ZoneDao extends CrudRepository<Zone, Integer> {
	
	Page<Zone> findByRestaurant(Restaurant restaurant, Pageable pageable);
	
}