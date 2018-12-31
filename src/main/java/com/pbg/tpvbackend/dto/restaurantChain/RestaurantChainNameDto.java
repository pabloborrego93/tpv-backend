package com.pbg.tpvbackend.dto.restaurantChain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class RestaurantChainNameDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2750458120864128384L;
	
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private String name;

	public RestaurantChainNameDto() {
		super();
	}

	public RestaurantChainNameDto(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
