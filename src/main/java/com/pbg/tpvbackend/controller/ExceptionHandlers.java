package com.pbg.tpvbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.google.common.collect.Lists;
import com.pbg.tpvbackend.config.BaseExceptionHandler;
import com.pbg.tpvbackend.exception.BadRequestException;
import com.pbg.tpvbackend.exception.UserAlreadyExistsException;

@ControllerAdvice
public class ExceptionHandlers extends BaseExceptionHandler {
	
	public ExceptionHandlers() {
		
		registerMapping(
			Lists.newArrayList(
				UserAlreadyExistsException.class
			),  HttpStatus.CONFLICT, "USER_ALREADY_EXISTS"
		);
        
		registerMapping(
			Lists.newArrayList(
				BadRequestException.class, 
				IllegalArgumentException.class, 
				MethodArgumentTypeMismatchException.class
			), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString()
		);
	
    }
	
}