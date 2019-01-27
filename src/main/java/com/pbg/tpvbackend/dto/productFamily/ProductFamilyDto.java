package com.pbg.tpvbackend.dto.productFamily;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFamilyDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7610061875095497627L;
	
	@Getter @Setter private Integer id;
	
	@Getter @Setter private String name;
	
	@Getter @Setter private Boolean catalogable;
	
}
