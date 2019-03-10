package com.pbg.tpvbackend.dto.order;

import java.io.Serializable;

import com.pbg.tpvbackend.dto.product.ProductNameDto;

import lombok.Getter;
import lombok.Setter;

public class OrderLineDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337689304870517249L;

	@Getter @Setter private Integer amount;
	
	@Getter @Setter private ProductNameDto product;
	
	@Getter @Setter private Double total;
	
}
