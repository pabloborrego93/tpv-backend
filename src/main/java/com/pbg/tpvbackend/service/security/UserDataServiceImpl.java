package com.pbg.tpvbackend.service.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.security.CustomUserDetails;
import com.pbg.tpvbackend.model.security.RoleName;
import com.pbg.tpvbackend.service.RestaurantChainService;

@Service
public class UserDataServiceImpl implements UserDataService {

	RestaurantChainService restaurantChainService;
	
	public UserDataServiceImpl(RestaurantChainService restaurantChainService) {
		super();
		this.restaurantChainService = restaurantChainService;
	}

	private CustomUserDetails getAuthentication() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) auth.getCredentials();
		return userDetails;
	}
	
	@Override
	public String getUsername() {
		return getAuthentication().getUsername();
	}

	@Override
	public List<RoleName> getRoles() {
		return getAuthentication().getRoles();
	}

	@Override
	public Boolean hasRole(RoleName role) {
		List<RoleName> roles = this.getRoles();
		Optional<RoleName> rol = roles
			.stream()
			.filter(r -> r.equals(role))
			.findAny();
			if(rol.isPresent()) {
				return Boolean.TRUE;
			}
		return Boolean.FALSE;
	}

	@Override
	public RestaurantChain chain() {
		return restaurantChainService.findById(getAuthentication().getChainId());
	}
	
}
