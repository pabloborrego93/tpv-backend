package com.pbg.tpvbackend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.user.UserBasicInfoDto;
import com.pbg.tpvbackend.dto.user.UserExtendedInfoDto;
import com.pbg.tpvbackend.dto.user.UserPostDto;
import com.pbg.tpvbackend.dto.user.UserUpdateDto;
import com.pbg.tpvbackend.exception.UserAlreadyExistsException;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/user")
public class UserController {

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
	@ApiOperation("Create User operation")
	@PostMapping
    public ResponseEntity<?> registerUser(
    	@ApiParam(required = true) @RequestBody(required = true) UserPostDto userPostDto) throws UserAlreadyExistsException {
		Optional<UserBasicInfoDto> user = userService.registerUser(userPostDto);
		if(user.isPresent());
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

	@PutMapping
	public ResponseEntity<?> updateUser(@ApiParam(required = true) @RequestBody(required = true) UserUpdateDto userUpdateDto) throws UserNotFoundException {
		Optional<UserExtendedInfoDto> user = userService.updateUser(userUpdateDto);
		if(user.isPresent());
			return ResponseEntity.ok(user);
	}
 	
	@GetMapping
    public ResponseEntity<?> getUserBasicData() throws UserNotFoundException {
		Optional<UserExtendedInfoDto> user = userService.getUserBasicData();
		if(user.isPresent());
			return ResponseEntity.ok(user);
    }
	
	@GetMapping("/search")
	public Page<UserExtendedInfoDto> search(
		@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, 
		@RequestParam(value = "max_per_page", required = false, defaultValue = "10") Integer max_per_page) throws UserNotFoundException {
		return userService.findByChainPaged(page, max_per_page);
	}
	
}