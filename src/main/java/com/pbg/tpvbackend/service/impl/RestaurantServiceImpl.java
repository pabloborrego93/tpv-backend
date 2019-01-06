package com.pbg.tpvbackend.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pbg.tpvbackend.dao.RestaurantDao;
import com.pbg.tpvbackend.dto.restaurant.RestaurantDto;
import com.pbg.tpvbackend.dto.restaurant.RestaurantPostDto;
import com.pbg.tpvbackend.dto.user.UserExtendedInfoDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantAlreadyExists;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.mapper.RestaurantMapper;
import com.pbg.tpvbackend.mapper.UserMapper;
import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.security.User;
import com.pbg.tpvbackend.service.RestaurantChainService;
import com.pbg.tpvbackend.service.RestaurantService;
import com.pbg.tpvbackend.service.UserService;
import com.pbg.tpvbackend.service.security.UserDataService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
	
	UserDataService userDataService;
	RestaurantChainService restaurantChainService;
	RestaurantMapper restaurantMapper;
	RestaurantDao restaurantDao;
	UserMapper userMapper;
	UserService userService;
	
	@Override
	public RestaurantDto create(RestaurantPostDto restaurantPostDto) throws UserNotFoundException, RestaurantAlreadyExists, UserWithoutRestaurantChain {
		RestaurantChain chain = restaurantChainService.findChainByUser();
		Optional<Restaurant> restaurant = chain
			.getRestaurants()
			.stream()
			.filter(rest -> rest.getName().toUpperCase().equals(restaurantPostDto.getName().toUpperCase()))
			.findFirst();
		if(restaurant.isPresent()) {
			throw new RestaurantAlreadyExists(restaurant.get().getName());
		}
		Restaurant newRestaurant = restaurantMapper.restaurantPostDtoAsRestaurant(restaurantPostDto);
		newRestaurant.setChainRestaurant(chain);
		newRestaurant = restaurantDao.save(newRestaurant);
		chain.getRestaurants().add(newRestaurant);
		restaurantChainService.update(chain);
		return restaurantMapper.asRestaurantDto(newRestaurant);
	}

	@Override
	public RestaurantDto findOne(String name) throws UserNotFoundException, UserWithoutRestaurantChain, RestaurantNotFoundException {
		RestaurantChain chain = restaurantChainService.findChainByUser();
		
		Optional<Restaurant> restaurant = chain
			.getRestaurants()
			.stream()
			.filter(rest -> rest.getName().toUpperCase().equals(name.toUpperCase()))
			.findFirst();
		
		if(!restaurant.isPresent()) {
			throw new RestaurantNotFoundException(name);
		}
		
		return restaurantMapper.asRestaurantDto(restaurant.get());
	}

	@Override
	public List<UserExtendedInfoDto> getWorkers(Integer id) throws RestaurantNotFoundException {
		Optional<Restaurant> restaurant = restaurantDao.findById(id);
		if(restaurant.isPresent()) {
			return restaurant.get().getWorkers().stream().map((u) -> userMapper.asUserExtendedInfoDto(u)).collect(Collectors.toList());
		} else {
			throw new RestaurantNotFoundException();
		}
	}

	@Override
	public List<UserExtendedInfoDto> setWorkers(Integer id, ArrayList<UserExtendedInfoDto> workers) throws RestaurantNotFoundException {
		Optional<Restaurant> restaurant = restaurantDao.findById(id);
		if(restaurant.isPresent()) {
			Set<User> users = new HashSet<>();
			for(UserExtendedInfoDto userInfo: workers) {
				users.add(userService.findById(Integer.parseInt(userInfo.getId())));
			}
			Restaurant rest = restaurant.get();
			rest.setWorkers(users);
			rest = restaurantDao.save(rest);
			return rest.getWorkers().stream().map((u) -> userMapper.asUserExtendedInfoDto(u)).collect(Collectors.toList());
		} else {
			throw new RestaurantNotFoundException();
		}
	}

	@Override
	public Restaurant findById(Integer id) {
		return restaurantDao.findById(id).get();
	}

	@Override
	public List<Restaurant> findRestaurantsByWorkerOrderByName(User user) {
		return restaurantDao.findByWorkersInOrderByName(user);
	}

}
