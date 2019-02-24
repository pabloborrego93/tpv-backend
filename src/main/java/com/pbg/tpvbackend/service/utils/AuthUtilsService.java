package com.pbg.tpvbackend.service.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.pbg.tpvbackend.exception.InvalidJWTException;
import com.pbg.tpvbackend.exception.UserNotFoundException;

public interface AuthUtilsService {

	public Date getExpTime();
	public Object getAuthentication(HttpServletRequest request) throws InvalidJWTException;
	public String generateTokenFromBDInfo() throws UserNotFoundException;
	
}
