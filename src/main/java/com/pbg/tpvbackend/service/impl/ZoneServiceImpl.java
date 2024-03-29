package com.pbg.tpvbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.collect.Lists;
import com.pbg.tpvbackend.dao.ZoneDao;
import com.pbg.tpvbackend.dto.zone.ZoneDto;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.exception.zone.ZoneNotFoundException;
import com.pbg.tpvbackend.mapper.ZoneMapper;
import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.Zone;
import com.pbg.tpvbackend.service.RestaurantService;
import com.pbg.tpvbackend.service.ZoneService;

import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class ZoneServiceImpl implements ZoneService {

	ZoneDao zoneDao;
	ZoneMapper zoneMapper;
	RestaurantService restaurantService;
	
	public Zone findById(Integer id) throws ZoneNotFoundException {
		Optional<Zone> zone = zoneDao.findById(id);
		if(zone.isPresent()) {
			return zone.get();
		} else {
			throw new ZoneNotFoundException();
		}
	}
	
	public List<Zone> findAllById(List<Integer> ids) {
		return Lists.newArrayList(zoneDao.findAllById(ids));
	}
	
	public Zone save(Zone zone) {
		return zoneDao.save(zone);
	}
	
	public void delete(Zone zone) {
		zoneDao.delete(zone);
	}
	
	public void deleteById(Integer id) {
		zoneDao.deleteById(id);
	}
	
	public Page<ZoneDto> findByRestaurant(Integer idRestaurant, Integer page, Integer max_per_page) throws RestaurantNotFoundException {
		Restaurant restaurant = restaurantService.findById(idRestaurant);
		return zoneDao.findByRestaurant(restaurant, PageRequest.of(page, max_per_page)).map((z) -> zoneMapper.asZoneDto(z));
	}

	@Override
	public ZoneDto postZoneByRestaurant(Integer id, ZoneDto zoneDto) throws RestaurantNotFoundException {
		Restaurant restaurant = restaurantService.findById(id);
		Zone zone = new Zone();
		zone.setRestaurant(restaurant);
		zone.setZoneType(zoneDto.getZoneType());
		zone.setDescription(zoneDto.getDescription());
		zone = zoneDao.save(zone);
		return zoneMapper.asZoneDto(zone);
	}

	@Override
	public ZoneDto putZoneByRestaurant(Integer id, ZoneDto zoneDto) throws NumberFormatException, ZoneNotFoundException {
		Zone zone = this.findById(Integer.parseInt(zoneDto.getId()));
		zone.setZoneType(zoneDto.getZoneType());
		zone.setDescription(zoneDto.getDescription());
		zone = zoneDao.save(zone);
		return zoneMapper.asZoneDto(zone);
	}

	@Override
	public void deleteZoneByRestaurant(Integer idRestaurant, Integer idZone) {
		zoneDao.deleteById(idZone);
	}
	
}
