package com.pbg.tpvBackEnd.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class RestaurantChainPostDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8739934970753260179L;

	@Getter @Setter @NonNull
	private String name;

	public RestaurantChainPostDto() {
		super();
	}
	
}
