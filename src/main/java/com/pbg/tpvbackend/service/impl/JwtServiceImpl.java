package com.pbg.tpvbackend.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pbg.tpvbackend.architecture.config.AppProperties;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.mapper.RestaurantChainMapper;
import com.pbg.tpvbackend.mapper.RestaurantMapper;
import com.pbg.tpvbackend.model.security.User;
import com.pbg.tpvbackend.service.JwtService;
import com.pbg.tpvbackend.service.UserService;
import com.pbg.tpvbackend.service.utils.AuthUtilsService;
import com.pbg.tpvbackend.utils.AppConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {

	private AppProperties appProperties;
	private UserService userService;
	private AuthUtilsService authUtilsService;
	private RestaurantMapper restaurantMapper;
	private RestaurantChainMapper restaurantChainMapper;
	
	@Override
	public String jwt(RestaurantChainDto chainDto) throws UserNotFoundException {
		User user = userService.findByUsername();
		
		Map<String, Object> claims = new HashMap<String, Object>();
		// Roles
		List<String> roles = user.getRoles().stream().map(r -> r.getName().toString()).collect(Collectors.toList());
		claims.put(AppConstants.getJWT_ROLES(), roles);
		// Name
		claims.put(AppConstants.getJWT_FIRSTNAME(), user.getFirstname());
		// Surname
		claims.put(AppConstants.getJWT_LASTNAME(), user.getLastname());
		// Email
		claims.put(AppConstants.getJWT_EMAIL(), user.getEmail());
		// RestaurantDto
//		claims.put(AppConstants.getJWT_RESTAURANT_DTO(), restaurantMapper.asRestaurantDto(user.getWorksIn()));
		// RestaurantChainDto
//		claims.put(AppConstants.getJWT_RESTAURANT_CHAIN_DTO(), chainDto);
		
		return Jwts.builder()
			.setSubject(user.getUsername())
			.setExpiration(authUtilsService.getExpTime())
			.setIssuedAt(Calendar.getInstance().getTime())
			.addClaims(claims)
			.signWith(SignatureAlgorithm.HS512, appProperties.getJwt().getSecret().getBytes()).compact();
	}
	
}
