package com.pbg.tpvbackend.dto;

import java.io.Serializable;

import org.springframework.validation.FieldError;

import lombok.Getter;
import lombok.Setter;

public class FieldErrorMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5359637410446942738L;
	
	@Getter @Setter
	private String field;
	@Getter @Setter
	private String message;

	public FieldErrorMessage() {
		super();
	}
	
    public FieldErrorMessage(FieldError error) {
        this.field = error.getField();
        this.message = error.getDefaultMessage();
    }
    
    public FieldErrorMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

}