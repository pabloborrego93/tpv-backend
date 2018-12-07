package com.pbg.tpvbackend.dto.product;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductNameDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9030086020137676707L;

	@Getter @Setter private Integer id;
	
	@Getter @Setter private String name;
	
}
