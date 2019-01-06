package com.pbg.tpvbackend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.security.User;

@Repository
public interface RestaurantDao extends CrudRepository<Restaurant, Integer> {

	List<Restaurant> findByWorkersInOrderByName(User user);
	
}
