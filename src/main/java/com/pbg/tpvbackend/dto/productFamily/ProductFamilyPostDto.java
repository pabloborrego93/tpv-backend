package com.pbg.tpvbackend.dto.productFamily;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFamilyPostDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5759306083813902869L;
	
	@NotNull(message = "Name can not be null")
    @NotBlank(message = "Name can not be blank")
	@Size(min = 2, max = 32, message = "Name length must be between 2 and 32 chars")
	@Getter @Setter private String name;
	
	@NotNull(message = "Catalogable can not be null")
	@Getter @Setter private Boolean catalogable;
	
}
