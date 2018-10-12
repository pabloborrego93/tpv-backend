package com.pbg.tpvbackend.service;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pbg.tpvbackend.architecture.annotation.Loggable;
import com.pbg.tpvbackend.dao.security.RoleDao;
import com.pbg.tpvbackend.dao.security.UserDao;
import com.pbg.tpvbackend.dto.user.UserBasicInfoDto;
import com.pbg.tpvbackend.dto.user.UserExtendedInfoDto;
import com.pbg.tpvbackend.dto.user.UserPostDto;
import com.pbg.tpvbackend.dto.user.UserUpdateDto;
import com.pbg.tpvbackend.exception.UserAlreadyExistsException;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.mapper.UserMapper;
import com.pbg.tpvbackend.model.security.RoleName;
import com.pbg.tpvbackend.model.security.User;
import com.pbg.tpvbackend.service.security.UserDataService;

import lombok.AllArgsConstructor;

@Validated
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
    private UserDao userDao;
	private RoleDao roleDao;
	private UserMapper userMapper;
	private BCryptPasswordEncoder bcrypt;
	private UserDataService userDataService;
	
	@Loggable
	@Override
	public Optional<UserBasicInfoDto> registerUser(@Valid UserPostDto userPostDto) throws UserAlreadyExistsException {
		Optional<User> optionalUser = userDao.findByUsername(userPostDto.getUsername());
		
		if(optionalUser.isPresent()) 
			throw new UserAlreadyExistsException();
		
		User user = userMapper.asEntity(userPostDto);
		user.setEnabled(Boolean.TRUE);
		user.setPassword(bcrypt.encode(userPostDto.getPassword()));
		user.setLastPasswordResetDate(new Date());
		user.getRoles().add(roleDao.findByName(RoleName.ROLE_RESTAURANT_CHAIN_ADMIN));
		
		return Optional.ofNullable(userMapper.asUserBasicInfoDto(userDao.save(user)));
	}

	@Loggable
	@Override
	public Optional<UserExtendedInfoDto> getUserBasicData() throws UserNotFoundException {
		Optional<User> optionalUser = userDao.findByUsername(userDataService.getUsername());
		if(optionalUser.isPresent()) 
			return Optional.ofNullable(userMapper.asUserExtendedInfoDto(optionalUser.get()));
		else 
			throw new UserNotFoundException();
	}

	@Loggable
	@Override
	public Optional<UserExtendedInfoDto> updateUser(@Valid UserUpdateDto userUpdateDto) throws UserNotFoundException {
		Optional<User> optionalUser = userDao.findByUsername(userDataService.getUsername());
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setEmail(userUpdateDto.getEmail());
			user.setFirstname(userUpdateDto.getFirstname());
			user.setLastname(userUpdateDto.getLastname());
			user = userDao.save(user);
			return Optional.ofNullable(userMapper.asUserExtendedInfoDto(user));
		} else 
			throw new UserNotFoundException();
	}
	
}

