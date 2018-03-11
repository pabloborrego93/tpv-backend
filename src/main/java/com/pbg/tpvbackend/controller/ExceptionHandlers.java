package com.pbg.tpvbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.pbg.tpvbackend.config.BaseExceptionHandler;
import com.pbg.tpvbackend.exception.BadRequestException;
import com.pbg.tpvbackend.exception.UserAlreadyExistsException;

@ControllerAdvice
public class ExceptionHandlers extends BaseExceptionHandler {
	
	public ExceptionHandlers() {
		
		registerMapping(
			UserAlreadyExistsException.class, HttpStatus.CONFLICT.value(), "USER_ALREADY_EXISTS", HttpStatus.CONFLICT
		);
        
		registerMapping(
			BadRequestException.class, HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", HttpStatus.BAD_REQUEST
		);
		
		registerMapping(
			IllegalArgumentException.class, HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", HttpStatus.BAD_REQUEST
		);
		
    }
	
}