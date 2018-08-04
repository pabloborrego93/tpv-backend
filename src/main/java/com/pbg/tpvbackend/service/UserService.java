package com.pbg.tpvbackend.service;

import java.util.Optional;

import javax.validation.Valid;

import com.pbg.tpvbackend.dto.user.UserBasicInfoDto;
import com.pbg.tpvbackend.dto.user.UserExtendedInfoDto;
import com.pbg.tpvbackend.dto.user.UserPostDto;
import com.pbg.tpvbackend.exception.UserAlreadyExistsException;
import com.pbg.tpvbackend.exception.UserNotFoundException;

public interface UserService {

	public Optional<UserBasicInfoDto> registerUser(@Valid UserPostDto userPostDto) throws UserAlreadyExistsException;
	public Optional<UserExtendedInfoDto> getUserBasicData() throws UserNotFoundException;
	
}
