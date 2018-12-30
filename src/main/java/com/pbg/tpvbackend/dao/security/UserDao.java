package com.pbg.tpvbackend.dao.security;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.security.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
	
	@EntityGraph(value = "graph.User.basicInfo")
	Optional<User> findByUsername(String username);

	@EntityGraph(value = "graph.User.basicInfo")
	Page<User> findByChainAndUsernameNot(RestaurantChain chain, String username, Pageable pageable);
	
}