package com.pbg.tpvbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pbg.tpvbackend.dao.RestaurantChainDao;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainDto;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainPostDto;
import com.pbg.tpvbackend.mapper.RestaurantChainMapper;
import com.pbg.tpvbackend.model.RestaurantChain;

@Service
public class RestaurantChainServiceImpl implements RestaurantChainService {

	@Autowired
	RestaurantChainDao restaurantChainDao;
	
	@Autowired
	RestaurantChainMapper restaurantChainMapper;
	
	@Override
	public RestaurantChainDto create(RestaurantChainPostDto restaurantChainPostDto) {
		RestaurantChain restChain = restaurantChainMapper.asRestaurantChain(restaurantChainPostDto);
		restChain = restaurantChainDao.save(restChain);
		return restaurantChainMapper.asRestaurantChainDto(restChain);
	}

	@Override
	public Page<RestaurantChainDto> findByName(String name, Integer page, Integer max) {
		Page<RestaurantChain> resultsPage = restaurantChainDao.findByNameStartsWithIgnoreCase(name, PageRequest.of(page, max));
		return resultsPage.map(restChain -> restaurantChainMapper.asRestaurantChainDto(restChain));
	}

}
