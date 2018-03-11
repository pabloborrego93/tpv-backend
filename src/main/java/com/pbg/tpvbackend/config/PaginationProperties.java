package com.pbg.tpvbackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties
@PropertySource("classpath:properties/pagination.properties")
public class PaginationProperties {

	@Getter @Setter
	private static Integer max_elements_per_page;
	@Getter @Setter
	private static Integer minimum_elements_per_page;
	
}
