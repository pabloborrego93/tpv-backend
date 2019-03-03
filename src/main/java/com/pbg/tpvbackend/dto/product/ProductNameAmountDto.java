package com.pbg.tpvbackend.dto.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pbg.tpvbackend.model.product.ProductType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	
	@Getter @Setter private Boolean forKitchen;
	
	@Getter @Setter private Boolean catalogable;
	
	@Getter @Setter private ProductType productType;
	
	@Getter @Setter List<ProductNameAmountDto> products = new ArrayList<>();
	
}
