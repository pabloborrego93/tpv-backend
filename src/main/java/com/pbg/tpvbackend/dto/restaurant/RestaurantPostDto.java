package com.pbg.tpvbackend.dto.restaurant;

import java.io.Serializable;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class RestaurantPostDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 158482680707823056L;

	@Getter @Setter @NonNull
	private String name;
	
	@Getter @Setter @NonNull
	private String address;
	
	public RestaurantPostDto() {
		super();
	}
	
}
