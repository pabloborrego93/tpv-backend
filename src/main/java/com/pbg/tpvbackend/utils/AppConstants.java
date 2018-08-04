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
}
