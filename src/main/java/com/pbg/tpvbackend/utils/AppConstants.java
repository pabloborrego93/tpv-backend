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
	private static final String JWT_RESTAURANT_DTO = "restaurantDto";
	@Getter
	private static final String JWT_RESTAURANT_CHAIN_DTO = "restaurantChainDto";
	@Getter
	private static final String JWT_EMAIL = "email";
	@Getter
	private static final String JWT_CHAIN_ID = "chainId";
	@Getter
	private static final String JWT_USER_ID = "userId";
	
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
	@Getter
	private static final String ERR_USER_WITHOUT_USERS = "User [%s] didnt configurated any user";
	@Getter
	private static final String ERR_PRODUCT_FAMILY_NOT_FOUND = "User [%s] hasnt got product family [%s]";
	@Getter
	private static final String ERR_INVALID_PRODUCT_TYPE = "ProductType [%s] doesnt exists";
	@Getter
	private static final String ERR_PRODUCT_NOT_FOUND = "Product [%s] doesnt exists";
	@Getter
	private static final String PRODUCT_NOT_FOUND = "Product [%s] doesnt exists, lets create";
	@Getter
	private static final String PRODUCT_ALREADY_EXISTS = "Product [%s] already exists";
	@Getter
	private static final String ERR_PRODUCT_UPDATE = "Cant update product [%s]";
	@Getter
	private static final String ERR_STATISTICS = "Cant generate statistics for user [%s]";
}
