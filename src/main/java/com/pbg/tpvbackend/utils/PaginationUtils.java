package com.pbg.tpvbackend.utils;

import com.pbg.tpvbackend.config.ConfigProperties;

public class PaginationUtils {

	public static Boolean isValidPageAndMaxPerPage(Integer page, Integer max_per_page) {
		return page instanceof Integer
			&& max_per_page instanceof Integer
			&& (page >= 0) 
			&& (max_per_page > 0) 
			&& (max_per_page <= ConfigProperties.getMax_elements_per_page())
			&& (max_per_page >= ConfigProperties.getMinimum_elements_per_page());
	}
	
}
