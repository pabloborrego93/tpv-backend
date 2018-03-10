package com.pbg.tpvbackend.dao.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.security.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer>{
	User findByUsername(String username);
}