package com.pbg.tpvbackend.dto.restaurantChain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pbg.tpvbackend.dto.restaurant.RestaurantDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class RestaurantChainDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2806623821399788978L;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private List<RestaurantDto> restaurants;
	
	public RestaurantChainDto() {
		super();
		this.restaurants = new ArrayList<RestaurantDto>();
	}

	public RestaurantChainDto(String name, List<RestaurantDto> restaurants) {
		super();
		this.name = name;
		this.restaurants = restaurants;
	}
	
}
