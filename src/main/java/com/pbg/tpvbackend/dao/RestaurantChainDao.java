package com.pbg.tpvbackend.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.security.User;

@Repository
public interface RestaurantChainDao extends PagingAndSortingRepository<RestaurantChain, Integer> {

	@EntityGraph(value = "graph.RestaurantChain.allCollectionsFirstLevel")
	Page<RestaurantChain> findByNameStartsWithIgnoreCase(@Param(value = "name") String name, Pageable pageable);
	
	@EntityGraph(value = "graph.RestaurantChain.allCollectionsFirstLevel")
	Optional<RestaurantChain> findByNameStartsWithIgnoreCaseAndOwner(
		@Param(value = "name") String name,
		@Param(value = "user") User user
	);
	
}
