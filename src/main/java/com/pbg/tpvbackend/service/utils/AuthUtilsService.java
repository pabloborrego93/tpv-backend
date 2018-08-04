package com.pbg.tpvbackend.service.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public interface AuthUtilsService {

	public Date getExpTime();
	public Object getAuthentication(HttpServletRequest request);
	
}
