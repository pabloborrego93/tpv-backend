package com.pbg.tpvbackend.utils;

import java.util.Calendar;
import java.util.Date;

import com.pbg.tpvbackend.config.ConfigProperties;

public class JwtUtils {

	public static Date getExpTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, ConfigProperties.getExpiration_time());
		return calendar.getTime();
	}
	
}
