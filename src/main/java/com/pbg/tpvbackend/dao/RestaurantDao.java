package com.pbg.tpvbackend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.Restaurant;

@Repository
public interface RestaurantDao extends CrudRepository<Restaurant, Integer> {

}
