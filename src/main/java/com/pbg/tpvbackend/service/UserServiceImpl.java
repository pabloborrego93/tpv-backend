package com.pbg.tpvbackend.service;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pbg.tpvbackend.dao.security.RoleDao;
import com.pbg.tpvbackend.dao.security.UserDao;
import com.pbg.tpvbackend.dto.user.UserBasicInfoDto;
import com.pbg.tpvbackend.dto.user.UserExtendedInfoDto;
import com.pbg.tpvbackend.dto.user.UserPostDto;
import com.pbg.tpvbackend.exception.UserAlreadyExistsException;
import com.pbg.tpvbackend.mapper.UserMapper;
import com.pbg.tpvbackend.model.security.RoleName;
import com.pbg.tpvbackend.model.security.User;

@Service
@Validated
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public Optional<UserBasicInfoDto> create(@Valid UserPostDto userPostDto) throws UserAlreadyExistsException {
		Optional<User> optionalUser = userDao.findByUsername(userPostDto.getUsername());
		
		if(optionalUser.isPresent()) 
			throw new UserAlreadyExistsException();
		
		User user = userMapper.asEntity(userPostDto);
		user.setEnabled(Boolean.TRUE);
		user.setPassword(bcrypt.encode(userPostDto.getPassword()));
		user.setLastPasswordResetDate(new Date());
		user.setRoles(Arrays.asList(roleDao.findByName(RoleName.ROLE_RESTAURANT_CHAIN_ADMIN)));
		
		return Optional.ofNullable(userMapper.asUserBasicInfoDto(userDao.save(user)));
	}

	@Override
	public Optional<UserExtendedInfoDto> get(Integer id) {
		Optional<User> optionalUser = userDao.findById(id);
		if(optionalUser.isPresent()) 
			return Optional.ofNullable(userMapper.asUserExtendedInfoDto(optionalUser.get()));
		else 
			return Optional.empty();
	}
	
}
