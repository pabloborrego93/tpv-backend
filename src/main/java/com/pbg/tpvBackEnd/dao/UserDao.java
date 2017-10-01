package com.pbg.tpvBackEnd.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pbg.tpvBackEnd.model.security.User;


@Repository
public interface UserDao extends PagingAndSortingRepository<User, Integer> {

	
	
}
