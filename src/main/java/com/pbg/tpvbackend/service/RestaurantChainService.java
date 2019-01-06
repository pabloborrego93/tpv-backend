package com.pbg.tpvbackend.service;

import java.util.Optional;

import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainDto;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainNameDto;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainPostDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.chain.ChainAlreadyExists;
import com.pbg.tpvbackend.exception.chain.ChainWithoutUsersException;
import com.pbg.tpvbackend.exception.user.UserAlreadyWithRestaurantChain;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.model.RestaurantChain;

public interface RestaurantChainService {

	public RestaurantChainDto create(RestaurantChainPostDto restaurantChainPostDto) throws UserNotFoundException, UserAlreadyWithRestaurantChain, ChainAlreadyExists;
	public Optional<RestaurantChainDto> findByUser() throws UserNotFoundException;
	public RestaurantChain findChainByUser() throws UserNotFoundException, UserWithoutRestaurantChain;
	public RestaurantChain update(RestaurantChain chain);
	public RestaurantChainNameDto getNameById(Integer id) throws ChainWithoutUsersException;
	public RestaurantChain findById(Integer id);
	
}
