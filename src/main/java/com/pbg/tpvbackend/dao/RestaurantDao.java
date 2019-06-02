package com.pbg.tpvbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.security.User;

@Repository
public interface RestaurantDao extends CrudRepository<Restaurant, Integer> {

	List<Restaurant> findByWorkersInOrderByName(User user);
	List<Restaurant> findByScreensInOrderByName(User user);
	
	@Query("SELECT count(r) FROM Restaurant r WHERE r.chainRestaurant = :restaurantChain")
	Integer countByChain(@Param("restaurantChain") RestaurantChain restaurantChain);
	
}
