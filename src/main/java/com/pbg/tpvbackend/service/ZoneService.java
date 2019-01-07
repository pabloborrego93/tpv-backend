package com.pbg.tpvbackend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pbg.tpvbackend.dto.zone.ZoneDto;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.exception.zone.ZoneNotFoundException;
import com.pbg.tpvbackend.model.Zone;

public interface ZoneService {

	public Zone findById(Integer id) throws ZoneNotFoundException;
	public List<Zone> findAllById(List<Integer> ids);
	public Zone save(Zone zone);
	public void delete(Zone zone);
	public void deleteById(Integer id);
	public Page<ZoneDto> findByRestaurant(Integer idRestaurant, Integer page, Integer max_per_page) throws RestaurantNotFoundException;
	public ZoneDto postZoneByRestaurant(Integer id, ZoneDto zoneDto) throws RestaurantNotFoundException;
	public ZoneDto putZoneByRestaurant(Integer id, ZoneDto zoneDto) throws NumberFormatException, ZoneNotFoundException;
	public void deleteZoneByRestaurant(Integer idRestaurant, Integer idZone);
	
}
