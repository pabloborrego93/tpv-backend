package com.pbg.tpvbackend.service;

import java.util.Optional;

import javax.validation.Valid;

import com.pbg.tpvbackend.dto.user.UserBasicInfoDto;
import com.pbg.tpvbackend.dto.user.UserPostDto;
import com.pbg.tpvbackend.exception.UserAlreadyExistsException;

public interface UserService {

	public Optional<UserBasicInfoDto> create(@Valid UserPostDto userPostDto) throws UserAlreadyExistsException;
	
}