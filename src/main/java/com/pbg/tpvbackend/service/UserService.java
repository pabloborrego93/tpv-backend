package com.pbg.tpvbackend.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.pbg.tpvbackend.dto.user.RestaurantChainUserPostDto;
import com.pbg.tpvbackend.dto.user.UserBasicInfoDto;
import com.pbg.tpvbackend.dto.user.UserExtendedInfoDto;
import com.pbg.tpvbackend.dto.user.UserPostDto;
import com.pbg.tpvbackend.dto.user.UserUpdateDto;
import com.pbg.tpvbackend.exception.UserAlreadyExistsException;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.model.security.User;

public interface UserService {

	public User save(User user);
	
	public Optional<UserBasicInfoDto> registerUser(@Valid UserPostDto userPostDto) throws UserAlreadyExistsException;
	public Optional<UserExtendedInfoDto> updateUser(@Valid UserUpdateDto userUpdateDto) throws UserNotFoundException;
	public Optional<UserExtendedInfoDto> getUserBasicData() throws UserNotFoundException;
	public User findByUsername() throws UserNotFoundException;
	
	public Optional<UserBasicInfoDto> registerRestaurantChainUser(@Valid RestaurantChainUserPostDto restaurantChainUserPostDto) throws UserNotFoundException, UserWithoutRestaurantChain, UserAlreadyExistsException;
	public Page<UserBasicInfoDto> findByChainPaged(Integer page, Integer max_per_page) throws UserNotFoundException;
	
}
