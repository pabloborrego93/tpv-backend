package com.pbg.tpvBackEnd.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class RestaurantChainDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2806623821399788978L;
	
	@Getter @Setter @NonNull
	private String name;
	
	@Getter @Setter @NonNull
	private List<RestaurantDto> restaurants = new ArrayList<RestaurantDto>();
	
	public RestaurantChainDto() {
		super();
	}
	
}
