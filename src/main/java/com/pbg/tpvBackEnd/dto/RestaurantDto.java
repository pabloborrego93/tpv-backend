package com.pbg.tpvBackEnd.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class RestaurantDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6968017180624688884L;
	
	@Getter @Setter @NonNull
	private String name;
	
	@Getter @Setter @NonNull
	private String address;
	
	public RestaurantDto() {
		super();
	}
	
}
