package com.pbg.tpvBackEnd.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pbg.tpvBackEnd.dao.security.RoleDao;
import com.pbg.tpvBackEnd.dao.security.UserDao;
import com.pbg.tpvBackEnd.dto.UserPostDto;
import com.pbg.tpvBackEnd.exception.UserAlreadyExistsException;
import com.pbg.tpvBackEnd.mapper.UserMapper;
import com.pbg.tpvBackEnd.model.security.RoleName;
import com.pbg.tpvBackEnd.model.security.User;

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
