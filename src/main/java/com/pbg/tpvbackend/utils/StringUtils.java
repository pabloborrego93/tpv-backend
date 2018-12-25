package com.pbg.tpvbackend.utils;

public class StringUtils {

	public static String getLastDotSplit(String string) {
		String[] stringArray = string.split("\\.");
		if(stringArray.length > 1) 
			return stringArray[stringArray.length-1];
		else
			throw new IllegalArgumentException();
	}
	
	public static String generateUserPrefix(Integer id, String username) {
		return String.format("%s-%s", id, username);
	}
	
}
