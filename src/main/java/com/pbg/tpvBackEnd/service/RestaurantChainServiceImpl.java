package com.pbg.tpvBackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pbg.tpvBackEnd.dao.RestaurantChainDao;
import com.pbg.tpvBackEnd.dto.RestaurantChainDto;
import com.pbg.tpvBackEnd.dto.RestaurantChainPostDto;
import com.pbg.tpvBackEnd.mapper.RestaurantChainMapper;
import com.pbg.tpvBackEnd.model.RestaurantChain;

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
		final PageRequest pageRequest = new PageRequest(page, max);
		Page<RestaurantChain> resultsPage = restaurantChainDao.findByNameStartsWithIgnoreCase(name, pageRequest);
		resultsPage.getContent().forEach(x->System.out.println(x.getRestaurants()));
		return resultsPage.map(restChain -> restaurantChainMapper.asRestaurantChainDto(restChain));
	}

}
