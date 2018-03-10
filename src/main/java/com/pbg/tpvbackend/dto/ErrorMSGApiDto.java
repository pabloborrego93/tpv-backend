package com.pbg.tpvbackend.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ErrorMSGApiDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2448010614525431384L;
	
	@Getter @Setter
	private Integer code;
	@Getter @Setter
	private String message;
	
	public ErrorMSGApiDto() {
		super();
	}
	
	public ErrorMSGApiDto(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
}
