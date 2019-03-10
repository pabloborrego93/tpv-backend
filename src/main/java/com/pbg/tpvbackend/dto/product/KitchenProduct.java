package com.pbg.tpvbackend.dto.product;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class KitchenProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6739233113341373934L;

	@Getter @Setter private Integer id;
	@Getter @Setter private String comment;
	
}
