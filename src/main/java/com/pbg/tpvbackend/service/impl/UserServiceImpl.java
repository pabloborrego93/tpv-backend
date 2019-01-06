package com.pbg.tpvbackend.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.collect.Lists;
import com.pbg.tpvbackend.architecture.annotation.Loggable;
import com.pbg.tpvbackend.dao.security.RoleDao;
import com.pbg.tpvbackend.dao.security.UserDao;
import com.pbg.tpvbackend.dto.role.RoleDto;
import com.pbg.tpvbackend.dto.user.RestaurantChainUserPostDto;
import com.pbg.tpvbackend.dto.user.RestaurantChainUserUpdateDto;
import com.pbg.tpvbackend.dto.user.UserBasicInfoDto;
import com.pbg.tpvbackend.dto.user.UserExtendedInfoDto;
import com.pbg.tpvbackend.dto.user.UserPostDto;
import com.pbg.tpvbackend.dto.user.UserUpdateDto;
import com.pbg.tpvbackend.exception.UserAlreadyExistsException;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.mapper.UserMapper;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.security.Role;
import com.pbg.tpvbackend.model.security.RoleName;
import com.pbg.tpvbackend.model.security.User;
import com.pbg.tpvbackend.service.RestaurantChainService;
import com.pbg.tpvbackend.service.UserService;
import com.pbg.tpvbackend.service.security.UserDataService;
import com.pbg.tpvbackend.utils.StringUtils;

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
	private RestaurantChainService restaurantChainService;
	
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
		for(RoleName roleName: RoleName.values()) {
			user.getRoles().add(roleDao.findByName(roleName));
		}
		
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

	@Loggable
	@Override
	public User findByUsername() throws UserNotFoundException {
		Optional<User> optionalUser = userDao.findByUsername(userDataService.getUsername());
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		} else
			throw new UserNotFoundException();
	}

	@Loggable
	@Override
	public Optional<UserBasicInfoDto> registerRestaurantChainUser(RestaurantChainUserPostDto restaurantChainUserPostDto) throws UserNotFoundException, UserWithoutRestaurantChain, UserAlreadyExistsException {
		RestaurantChain chain = restaurantChainService.findChainByUser();
		String userPrefix = StringUtils.generateUserPrefix(chain.getId(), restaurantChainUserPostDto.getUsername());
		Optional<User> optionalUser = userDao.findByUsername(userPrefix);
		if(optionalUser.isPresent()) 
			throw new UserAlreadyExistsException();
		else {
			User user = userMapper.asEntity(restaurantChainUserPostDto);
			user.setUsername(userPrefix);
			user.setEnabled(Boolean.TRUE);
			user.setPassword(bcrypt.encode(restaurantChainUserPostDto.getPassword()));
			user.setLastPasswordResetDate(new Date());
			user.setChain(chain);
			Set<Role> roles = new HashSet<>();
			for(RoleName roleName: restaurantChainUserPostDto.getRoles()) {
				roles.add(roleDao.findByName(roleName));
			}
			user.setRoles(roles);
			user = userDao.save(user);
			return Optional.of(userMapper.asUserBasicInfoDto(user));
		}
	}
	
	@Override
	public Optional<UserBasicInfoDto> updateRestaurantChainUser(RestaurantChainUserUpdateDto restaurantChainUserUpdateDto) throws UserNotFoundException {
		Optional<User> optionalUser = userDao.findByUsername(restaurantChainUserUpdateDto.getUsername());
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException();
		} else {
			User user = optionalUser.get();
			user.setEmail(restaurantChainUserUpdateDto.getEmail());
			user.setFirstname(restaurantChainUserUpdateDto.getFirstname());
			user.setLastname(restaurantChainUserUpdateDto.getLastname());
			Set<Role> roles = new HashSet<>();
			for(RoleDto roleName: restaurantChainUserUpdateDto.getRoles()) {
				roles.add(roleDao.findByName(roleName.getName()));
			}
			user.setRoles(roles);
			user = userDao.save(user);
			return Optional.of(userMapper.asUserBasicInfoDto(user));
		}
	}

	@Override
	public Page<UserExtendedInfoDto> findByChainPaged(Integer page, Integer max_per_page) throws UserNotFoundException {
		User user = this.findByUsername();
		RestaurantChain chain = user.getChainOwned();
		Page<User> users = userDao.findByChainAndUsernameNot(chain, user.getUsername(), PageRequest.of(page, max_per_page));
		return users.map((u) -> userMapper.asUserExtendedInfoDto(u));
	}

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	public void deleteRestaurantChainUser(Integer id) {
		userDao.deleteById(id);
	}

	@Override
	public List<UserExtendedInfoDto> findAll() throws UserNotFoundException {
		User user = this.findByUsername();
		RestaurantChain chain = user.getChainOwned();
		List<User> users = Lists.newArrayList(userDao.findByChain(chain));
		return users.stream().map((u) -> userMapper.asUserExtendedInfoDto(u)).collect(Collectors.toList());
	}

	@Override
	public User findById(Integer id) {
		return userDao.findById(id).get();
	}
	
}

