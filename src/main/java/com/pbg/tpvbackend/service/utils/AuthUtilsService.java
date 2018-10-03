package com.pbg.tpvbackend.service.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.pbg.tpvbackend.exception.InvalidJWTException;

public interface AuthUtilsService {

	public Date getExpTime();
	public Object getAuthentication(HttpServletRequest request) throws InvalidJWTException;
	
}
