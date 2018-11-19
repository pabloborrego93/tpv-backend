package com.pbg.tpvbackend.dto.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.pbg.tpvbackend.dto.productFamily.ProductFamilyDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -259759467093554636L;

	@Size(min = 2, max = 16, message = "Name length must be between 2 and 16 chars")
	@Getter @Setter private String name;
	
	@Builder.Default
	@Getter @Setter private List<ProductFamilyDto> families = new ArrayList<ProductFamilyDto>();

}
