package com.pbg.tpvbackend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.RestaurantChain;

@Repository
public interface RestaurantChainDao extends PagingAndSortingRepository<RestaurantChain, Integer> {

	@EntityGraph(value = "graph.RestaurantChain.restaurants")
	Page<RestaurantChain> findByNameStartsWithIgnoreCase(@Param(value = "name") String name, Pageable pageable);
	
}
