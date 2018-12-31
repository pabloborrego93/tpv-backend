package com.pbg.tpvbackend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.user.RestaurantChainUserPostDto;
import com.pbg.tpvbackend.dto.user.RestaurantChainUserUpdateDto;
import com.pbg.tpvbackend.dto.user.UserBasicInfoDto;
import com.pbg.tpvbackend.exception.UserAlreadyExistsException;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/restaurantChain/user")
@AllArgsConstructor
public class RestaurantChainUserController {

	@Autowired
	private UserService userService;
	
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "User created"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not found"),
		@ApiResponse(code = 409, message = "User already exists"),
		@ApiResponse(code = 422, message = "Invalid input"),
	})
	@ApiOperation("Create User for a restaurant chain")
	@PostMapping
    public ResponseEntity<?> registerUser(
    	@ApiParam(required = true) @RequestBody(required = true) RestaurantChainUserPostDto restaurantChainUserPostDto) throws UserAlreadyExistsException, UserNotFoundException, UserWithoutRestaurantChain {
		Optional<UserBasicInfoDto> user = userService.registerRestaurantChainUser(restaurantChainUserPostDto);
		if(user.isPresent());
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
	
	@PutMapping
    public ResponseEntity<?> updateUser(
    	@ApiParam(required = true) @RequestBody(required = true) RestaurantChainUserUpdateDto restaurantChainUserupdateDto) throws UserAlreadyExistsException, UserNotFoundException, UserWithoutRestaurantChain {
		Optional<UserBasicInfoDto> user = userService.updateRestaurantChainUser(restaurantChainUserupdateDto);
		if(user.isPresent());
			return ResponseEntity.ok().body(user);
    }
	
	@DeleteMapping
    public ResponseEntity<?> deleteUser(
    	@ApiParam(required = true) @RequestParam("id") Integer id) {
		userService.deleteRestaurantChainUser(id);
		return ResponseEntity.ok().build();
    }
	
}
