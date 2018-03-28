package com.pbg.tpvbackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties
@PropertySource("classpath:properties/config.properties")
public class ConfigProperties {

	/*
	 * Pagination Config
	 */
	@Getter @Setter
	private static Integer max_elements_per_page;
	@Getter @Setter
	private static Integer minimum_elements_per_page;
	/*
	 * Jwt Config
	 */
	@Getter
	private final static String TOKEN_PREFIX = "Bearer ";
	@Getter
	private final static String HEADER_STRING = "Authorization";
	@Getter @Setter
	private static String secret;
	@Getter @Setter
	private static Integer expiration_time;
	@Getter @Setter
	private static String sign_up_url;
	
}
