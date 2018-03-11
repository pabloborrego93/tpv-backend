package com.pbg.tpvbackend.utils;

import com.pbg.tpvbackend.config.GlobalProperties;

public class PaginationUtils {

	public static Boolean isValidPageAndMaxPerPage(Integer page, Integer max_per_page) {
		return (page >= 0) 
			&& (max_per_page > 0) 
			&& (max_per_page <= GlobalProperties.getMax_elements_per_page())
			&& (max_per_page >= GlobalProperties.getMinimum_elements_per_page());
	}
	
}
