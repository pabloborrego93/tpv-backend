package com.pbg.tpvbackend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.user.UserBasicInfoDto;
import com.pbg.tpvbackend.dto.user.UserPostDto;
import com.pbg.tpvbackend.exception.UserAlreadyExistsException;
import com.pbg.tpvbackend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
    public ResponseEntity<?> create(@RequestBody UserPostDto userPostDto) throws UserAlreadyExistsException {
		Optional<UserBasicInfoDto> user = userService.create(userPostDto);
		if(user.isPresent());
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
	
	@GetMapping("/test")
    public ResponseEntity<?> create() throws UserAlreadyExistsException {
		throw new UserAlreadyExistsException();
	}
	
}