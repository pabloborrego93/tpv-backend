package com.pbg.tpvbackend.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pbg.tpvbackend.model.Printer;
import com.pbg.tpvbackend.model.Restaurant;

public interface PrinterDao extends CrudRepository<Printer, Integer> {
	
	Page<Printer> findByRestaurantOwner(Restaurant restaurant, Pageable pageable);

	@Modifying
	@Query("UPDATE Printer p SET p.defaultPrinter = 0 WHERE p.restaurantOwner = :restaurant")
	void setAllPrintersAsNotDefault(@Param("restaurant") Restaurant restaurant);
	
	@Query("SELECT p FROM Printer p WHERE p.defaultPrinter = 1 AND p.restaurantOwner = :restaurant")
	Optional<Printer> findByDefaultPrinter(@Param("restaurant") Restaurant restaurant);
	
}