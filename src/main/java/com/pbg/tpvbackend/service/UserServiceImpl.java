package com.pbg.tpvbackend.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pbg.tpvbackend.dao.security.RoleDao;
import com.pbg.tpvbackend.dao.security.UserDao;
import com.pbg.tpvbackend.dto.user.UserPostDto;
import com.pbg.tpvbackend.exception.UserAlreadyExistsException;
import com.pbg.tpvbackend.mapper.UserMapper;
import com.pbg.tpvbackend.model.security.RoleName;
import com.pbg.tpvbackend.model.security.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserMapper userMapping;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public User create(UserPostDto userPostDto) throws UserAlreadyExistsException {
		User user = userDao.findByUsername(userPostDto.getUsername());
		if(user != null) throw new UserAlreadyExistsException();
		else user = userMapping.toEntity(userPostDto);
		user.setEnabled(true);
		user.setPassword(bcrypt.encode(userPostDto.getPassword()));
		user.setLastPasswordResetDate(new Date());
		user.setRoles(Arrays.asList(roleDao.findByName(RoleName.ROLE_RESTAURANT_CHAIN_ADMIN)));
		return userDao.save(user);
	}
	
}
