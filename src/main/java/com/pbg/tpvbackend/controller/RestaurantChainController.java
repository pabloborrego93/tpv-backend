package com.pbg.tpvbackend.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainDto;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainNameDto;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainPostDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.chain.ChainAlreadyExists;
import com.pbg.tpvbackend.exception.chain.ChainWithoutUsersException;
import com.pbg.tpvbackend.exception.user.UserAlreadyWithRestaurantChain;
import com.pbg.tpvbackend.service.RestaurantChainService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/restaurantChain")
@AllArgsConstructor
public class RestaurantChainController {

	private RestaurantChainService restaurantChainService;
	
	@PreAuthorize("hasRole('ROLE_RESTAURANT_CHAIN_ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody RestaurantChainPostDto restaurantChainPostDto) throws UserNotFoundException, UserAlreadyWithRestaurantChain, ChainAlreadyExists {
		RestaurantChainDto chain = restaurantChainService.create(restaurantChainPostDto);
		return ResponseEntity
			.ok()
			.body(chain);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findByUser() throws UserNotFoundException {
		Optional<RestaurantChainDto> chain = restaurantChainService.findByUser();
		return ResponseEntity.ok(chain);
	}
	
	@GetMapping("/getById")
	public ResponseEntity<?> findNameById(@RequestParam("id") Integer id) throws UserNotFoundException, ChainWithoutUsersException {
		RestaurantChainNameDto chain = restaurantChainService.getNameById(id);
		return ResponseEntity.ok(chain);
	}
	
}
