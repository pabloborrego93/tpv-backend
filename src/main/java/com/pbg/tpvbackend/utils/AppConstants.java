package com.pbg.tpvbackend.utils;

import lombok.Getter;

public class AppConstants {

	/*
	 * CONSTANTES
	 */
	@Getter
	private static final String TOKEN_PREFIX = "Bearer ";
	@Getter
	private static final String HEADER_STRING = "Authorization";
	@Getter
	private static final String ANONYMOUS_USER = "anonymous";
	@Getter
	private static final String JWT_ROLES = "roles";
	@Getter
	private static final String JWT_FIRSTNAME = "firstname";
	@Getter
	private static final String JWT_LASTNAME = "lastname";
	@Getter
	private static final String JWT_WORKSIN = "worksIn";
	@Getter
	private static final String JWT_EMAIL = "email";
	
	/*
	 * ERROR CONSTANTES
	 */
	@Getter
	private static final String ERR_JWT_EXPIRED = "JWT token is expired!";
	@Getter
	private static final String ERR_JWT_INVALID = "JWT token is invalid!";
	@Getter
	private static final String ERR_JWT_EMPTY = "JWT token is empty!";
	@Getter
	private static final String ERR_USER_WITHOUT_RESTAURANT_CHAIN = "User [%s] didnt configurated restaurant chain";
	@Getter
	private static final String ERR_USER_WITHOUT_RESTAURANTS = "User [%s] didnt configurated any restaurants";
	@Getter
	private static final String ERR_USER_WITHOUT_PRODUCT_FAMILIES = "User [%s] didnt configurated any product family";
	@Getter
	private static final String ERR_USER_WITHOUT_PRODUCTS = "User [%s] didnt configurated any product";
}
