package com.pbg.tpvbackend.dto.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pbg.tpvbackend.dto.productFamily.ProductFamilyDto;
import com.pbg.tpvbackend.model.product.IVA;
import com.pbg.tpvbackend.model.product.ProductType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsForOrderingDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7102795404686721273L;

	@Getter @Setter private Integer id;
	
	@Getter @Setter private String name;
	
	@Getter @Setter private String image;
	
	@Getter @Setter private Double price;
	
	@Getter @Setter private IVA iva;
	
	@Getter @Setter private Boolean forKitchen;
	
	@Getter @Setter private ProductType productType;
	
	@Builder.Default
	@Getter @Setter List<ProductFamilyDto> families = new ArrayList<>();
	
	@Builder.Default
	@Getter @Setter List<ProductNameAmountDto> products = new ArrayList<>();
	
}
