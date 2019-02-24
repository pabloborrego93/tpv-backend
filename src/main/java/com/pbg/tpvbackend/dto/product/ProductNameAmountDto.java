package com.pbg.tpvbackend.dto.product;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductNameAmountDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1919069889780150273L;

	@Getter @Setter private Integer id;
	
	@Getter @Setter private String name;
	
	@Getter @Setter private Integer amount;
	
}
