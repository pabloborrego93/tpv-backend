package com.pbg.tpvbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.pbg.tpvbackend.dto.zone.ZoneDto;
import com.pbg.tpvbackend.model.Zone;

public interface ZoneService {

	public Optional<Zone> findById(Integer id);
	public List<Zone> findAllById(List<Integer> ids);
	public Zone save(Zone zone);
	public void delete(Zone zone);
	public void deleteById(Integer id);
	public Page<ZoneDto> findByRestaurant(Integer idRestaurant, Integer page, Integer max_per_page);
	public ZoneDto postZoneByRestaurant(Integer id, ZoneDto zoneDto);
	public ZoneDto putZoneByRestaurant(Integer id, ZoneDto zoneDto);
	public void deleteZoneByRestaurant(Integer idRestaurant, Integer idZone);
	
}
