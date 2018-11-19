package com.pbg.tpvbackend.dto.product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.pbg.tpvbackend.dto.productFamily.ProductFamilyDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9030086020137676707L;

	@Getter @Setter private String name;
	
	@Getter @Setter private String image;
	
	@Builder.Default
	@Getter @Setter private Set<ProductFamilyDto> families = new HashSet<ProductFamilyDto>();
	
}
