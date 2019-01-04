package com.pbg.tpvbackend.dto.zone;

import java.io.Serializable;

import com.pbg.tpvbackend.dto.restaurant.RestaurantDto;
import com.pbg.tpvbackend.model.ZoneType;

import lombok.Getter;
import lombok.Setter;

public class ZoneDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8788371450600171600L;

	@Getter @Setter
	private String id;
	
	@Getter @Setter
	private ZoneType zoneType;
	
	@Getter @Setter
	private String description;
	
	@Getter @Setter
	private RestaurantDto restaurantDto;
	
}
