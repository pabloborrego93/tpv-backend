package com.pbg.tpvbackend.dto.restaurantChain;

import java.io.Serializable;

import javax.validation.constraints.Size;

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
	@Size(min = 2, max = 32, message = "Name length must be between 2 and 32 chars")
	private String name;

	public RestaurantChainPostDto() {
		super();
	}

	public RestaurantChainPostDto(String name) {
		super();
		this.name = name;
	}
	
}
