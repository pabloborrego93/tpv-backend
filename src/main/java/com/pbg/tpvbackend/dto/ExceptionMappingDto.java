package com.pbg.tpvbackend.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ExceptionMappingDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4423668874093829847L;
	
	
	@Getter @Setter
	private Integer code;
	@Getter @Setter
	private String message;
	@Getter @Setter
	private HttpStatus status;
	
	public ExceptionMappingDto() {
		super();
	}
	
	public ExceptionMappingDto(Integer code, String message, HttpStatus status) {
		super();
		this.code = code;
		this.message = message;
		this.status = status;
	}
	
}
