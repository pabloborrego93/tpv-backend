package com.pbg.tpvbackend.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.ErrorMSGApiDto;

//@RestController
//@RequestMapping(value = "/error")
public class ApiErrorController implements ErrorController {

	private final static String ERROR_PATH = "/error";
	
	@GetMapping
	public ResponseEntity<ErrorMSGApiDto> error() {
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(new ErrorMSGApiDto(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name()));
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
	
}
