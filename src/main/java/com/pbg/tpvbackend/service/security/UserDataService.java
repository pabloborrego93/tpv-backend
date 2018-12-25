package com.pbg.tpvbackend.service.security;

import java.util.List;

import com.pbg.tpvbackend.dto.restaurant.RestaurantDto;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainDto;
import com.pbg.tpvbackend.model.security.RoleName;

public interface UserDataService {

	public String getUsername();
	
	public List<RoleName> getRoles();
	
	public Boolean hasRole(RoleName role);
	
//	public RestaurantDto getRestaurantDto();
	
//	public RestaurantChainDto getRestaurantChainDto();
	
}
