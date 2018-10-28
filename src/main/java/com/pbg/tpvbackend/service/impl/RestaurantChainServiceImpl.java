package com.pbg.tpvbackend.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pbg.tpvbackend.dao.RestaurantChainDao;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainDto;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainPostDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.chain.ChainAlreadyExists;
import com.pbg.tpvbackend.exception.user.UserAlreadyWithRestaurantChain;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.mapper.RestaurantChainMapper;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.security.User;
import com.pbg.tpvbackend.service.RestaurantChainService;
import com.pbg.tpvbackend.service.UserService;
import com.pbg.tpvbackend.service.security.UserDataService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RestaurantChainServiceImpl implements RestaurantChainService {

	RestaurantChainDao restaurantChainDao;
	RestaurantChainMapper restaurantChainMapper;
	UserDataService userDataService;
	UserService userService;
	
	@Override
	public RestaurantChainDto create(RestaurantChainPostDto restaurantChainPostDto) throws UserNotFoundException, UserAlreadyWithRestaurantChain, ChainAlreadyExists {
		Optional<RestaurantChainDto> chain = this.findByUser();
		if(chain.isPresent()) {
			throw new UserAlreadyWithRestaurantChain();
		} else {
			Optional<RestaurantChain> chainCheck = restaurantChainDao.findByNameStartsWithIgnoreCaseAndOwner(restaurantChainPostDto.getName(), userService.findByUsername());
			if(chainCheck.isPresent()) {
				throw new ChainAlreadyExists(restaurantChainPostDto.getName());
			}
			User user = userService.findByUsername();
			RestaurantChain newChain = new RestaurantChain();
			newChain.setName(restaurantChainPostDto.getName());
			newChain.setOwner(user);
			newChain = restaurantChainDao.save(newChain);
			return restaurantChainMapper.asRestaurantChainDto(newChain);
		}
	}

	@Override
	public Optional<RestaurantChainDto> findByUser() throws UserNotFoundException  {
		User user = userService.findByUsername();
		RestaurantChain chain = user.getChain();
		if (chain == null) {
			return Optional.empty();
		} else {
			return Optional.of(restaurantChainMapper.asRestaurantChainDto(chain));
		}
	}

	@Override
	public RestaurantChain findChainByUser() throws UserNotFoundException, UserWithoutRestaurantChain {
		User user = userService.findByUsername();
		RestaurantChain chain = user.getChain();
		if (chain == null) {
			throw new UserWithoutRestaurantChain();
		} else {
			return chain;
		}
	}

	@Override
	public RestaurantChain update(RestaurantChain chain) {
		return restaurantChainDao.save(chain);
	}

}
