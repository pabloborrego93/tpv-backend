package com.pbg.tpvbackend.dto.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.pbg.tpvbackend.dto.productFamily.ProductFamilyDto;
import com.pbg.tpvbackend.model.product.IVA;

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
	
	@NotNull(message = "Price can not be null")
	@Getter @Setter private Double price;
	
	@NotNull(message = "IVA can not be null")
	@Getter @Setter private IVA iva;
	
	@Builder.Default
	@Getter @Setter List<ProductFamilyDto> families = new ArrayList<ProductFamilyDto>();
	
}
