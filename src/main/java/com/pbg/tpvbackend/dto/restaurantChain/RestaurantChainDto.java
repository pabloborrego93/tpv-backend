package com.pbg.tpvbackend.dto.restaurantChain;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pbg.tpvbackend.dto.productFamily.ProductFamilyDto;
import com.pbg.tpvbackend.dto.restaurant.RestaurantDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantChainDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2806623821399788978L;
	
	@Size(min = 2, max = 16, message = "Name length must be between 2 and 16 chars")
	@NotNull(message = "Name can not be null")
    @NotBlank(message = "Name can not be blank")
	@Getter @Setter private String name;
	
	@Getter @Setter private List<RestaurantDto> restaurants;
	
	@Getter @Setter private List<ProductFamilyDto> families;
	
}
