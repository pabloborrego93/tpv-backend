package com.pbg.tpvbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.google.common.collect.Lists;
import com.pbg.tpvbackend.architecture.config.BaseExceptionHandler;
import com.pbg.tpvbackend.exception.BadRequestException;
import com.pbg.tpvbackend.exception.InvalidJWTException;
import com.pbg.tpvbackend.exception.UserAlreadyExistsException;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.chain.ChainAlreadyExists;
import com.pbg.tpvbackend.exception.restaurant.RestaurantAlreadyExists;
import com.pbg.tpvbackend.exception.user.UserAlreadyWithRestaurantChain;

@ControllerAdvice
public class ExceptionHandlers extends BaseExceptionHandler {
	
	public ExceptionHandlers() {
        
		/**
		 * Error 400
		 */
		registerMapping(
			Lists.newArrayList(
				BadRequestException.class, 
				IllegalArgumentException.class, 
				MethodArgumentTypeMismatchException.class
			), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name()
		);
		
		/**
		 * Error 401
		 */
		registerMapping(
			Lists.newArrayList(
				AuthenticationException.class,
				InsufficientAuthenticationException.class,
				InvalidJWTException.class
			),  HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.name()
		);
		
		/**
		 * Error 403
		 */
		registerMapping(
			Lists.newArrayList(
				UsernameNotFoundException.class
			),  HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.name()
		);
		
		/**
		 * Error 404
		 */
		registerMapping(
			Lists.newArrayList(
				UserNotFoundException.class
			),  HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.name()
		);
		
		/**
		 * Error 405
		 */
		registerMapping(HttpRequestMethodNotSupportedException.class, HttpStatus.METHOD_NOT_ALLOWED, HttpStatus.METHOD_NOT_ALLOWED.name());
		
		/**
		 * Error 409
		 */
		registerMapping(
			Lists.newArrayList(
				UserAlreadyExistsException.class,
				UserAlreadyWithRestaurantChain.class,
				ChainAlreadyExists.class,
				RestaurantAlreadyExists.class
			),  HttpStatus.CONFLICT, "DATA_ALREADY_EXISTS"
		);
	
    }
	
}