package com.pbg.tpvbackend.dto.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class ProductPostDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7272442327746147889L;
	
	@NotNull(message = "Name can not be null")
    @NotBlank(message = "Name can not be blank")
	@Size(min = 2, max = 32, message = "Name length must be between 2 and 16 chars")
	@Getter @Setter private String name;
	
	@NotNull(message = "Price can not be null")
	@Getter @Setter private Double price;
	
	@NotNull(message = "IVA can not be null")
	@Getter @Setter private IVA iva;
	
	@NotNull(message = "Image can not be null")
    @NotBlank(message = "Image can not be blank")
	@Getter @Setter private String image;
	
	@NotNull(message = "Catalogable can not be null")
	@Getter @Setter private Boolean catalogable;
	
	@NotNull(message = "Can not be null")
	@Getter @Setter private Boolean forKitchen;
	
	@Builder.Default
	@Getter @Setter List<ProductFamilyDto> productFamilies = new ArrayList<ProductFamilyDto>();
	
	@NotNull(message = "ProductType can not be null")
	@Getter @Setter private ProductType productType;
	
	@Builder.Default
	@Getter @Setter List<ProductNameAmountDto> products = new ArrayList<>();
	
}
