package com.pbg.tpvbackend.service.security;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pbg.tpvbackend.model.security.CustomUserDetails;
import com.pbg.tpvbackend.model.security.Role;

@Service
public class UserDataServiceImpl implements UserDataService {

	private CustomUserDetails getAuthentication() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) auth.getCredentials();
		return userDetails;
	}
	
	@Override
	public String getUsername() {
		return getAuthentication().getUsername();
	}

	@Override
	public List<Role> getRoles() {
		return getAuthentication().getRoles();
	}
	
}
