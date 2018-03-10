package com.pbg.tpvbackend.service;

import com.pbg.tpvbackend.dto.user.UserPostDto;
import com.pbg.tpvbackend.exception.UserAlreadyExistsException;
import com.pbg.tpvbackend.model.security.User;

public interface UserService {

	public User create(UserPostDto userPostDto) throws UserAlreadyExistsException;
	
}
