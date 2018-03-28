package com.pbg.tpvbackend.dto.restaurantChain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class RestaurantChainPostDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8739934970753260179L;

	@Getter @Setter
	private String name;

	public RestaurantChainPostDto() {
		super();
	}

	public RestaurantChainPostDto(String name) {
		super();
		this.name = name;
	}
	
}
