package com.pbg.tpvBackEnd.service;

import com.pbg.tpvBackEnd.exception.UserAlreadyExistsException;
import com.pbg.tpvBackEnd.dto.UserPostDto;
import com.pbg.tpvBackEnd.model.security.User;

public interface UserService {

	public User create(UserPostDto userPostDto) throws UserAlreadyExistsException;
	
}
