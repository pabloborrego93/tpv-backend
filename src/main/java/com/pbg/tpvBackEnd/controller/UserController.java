package com.pbg.tpvBackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvBackEnd.dto.UserPostDto;
import com.pbg.tpvBackEnd.exception.UserAlreadyExistsException;
import com.pbg.tpvBackEnd.model.security.User;
import com.pbg.tpvBackEnd.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody UserPostDto userPostDto) throws UserAlreadyExistsException {
		User user = userService.create(userPostDto);
		if(user != null);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
	
}