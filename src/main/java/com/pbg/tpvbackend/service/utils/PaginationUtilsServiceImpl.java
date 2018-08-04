package com.pbg.tpvbackend.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbg.tpvbackend.architecture.config.AppProperties;

@Service
public class PaginationUtilsServiceImpl implements PaginationUtilsService {

	@Autowired
	private AppProperties appProperties;
	
	public Boolean isValidPageAndMaxPerPage(Integer page, Integer max_per_page) {
		return page instanceof Integer
			&& max_per_page instanceof Integer
			&& (page >= 0) 
			&& (max_per_page > 0) 
			&& (max_per_page <= appProperties.getPagination().getMaxElementsPerPage())
			&& (max_per_page >= appProperties.getPagination().getMinElementsPerPage());
	}
	
}
