package com.pbg.tpvbackend.service.security;

import java.util.List;

import com.pbg.tpvbackend.model.security.Role;

public interface UserDataService {

	public String getUsername();
	
	public List<Role> getRoles();
	
}
