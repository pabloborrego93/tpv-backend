package com.pbg.tpvbackend.dto.kitchen;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.pbg.tpvbackend.dto.product.ProductNameDto;
import com.pbg.tpvbackend.dto.zone.ZoneDto;
import com.pbg.tpvbackend.model.kitchen.KitchenProductStatus;

import lombok.Getter;
import lombok.Setter;

public class KitchenProductDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6912849743276061648L;

	@Getter @Setter private Integer id;
	
	@Getter @Setter private String comment;
	
	@Getter @Setter private ProductNameDto product;
	
	@Getter @Setter private ZoneDto zone;
	
	@Enumerated(EnumType.STRING)
	@Getter @Setter private KitchenProductStatus status;
	
}
