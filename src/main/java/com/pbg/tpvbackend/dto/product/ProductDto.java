package com.pbg.tpvbackend.dto.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pbg.tpvbackend.dto.productFamily.ProductFamilyDto;
import com.pbg.tpvbackend.model.product.IVA;
import com.pbg.tpvbackend.model.product.ProductType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9030086020137676707L;

	@Getter @Setter private Integer id;
	
	@Getter @Setter private String name;
	
	@Getter @Setter private Double price;
	
	@Getter @Setter private IVA iva;
	
	@Getter @Setter private String image;
	
	@Getter @Setter private Boolean catalogable;
	
	@Getter @Setter private Boolean forKitchen;
	
	@Getter @Setter private ProductType productType;
	
	@Getter @Setter List<ProductNameAmountDto> products = new ArrayList<>();
	
	@Getter @Setter private Set<ProductFamilyDto> families = new HashSet<>();
	
	
}
