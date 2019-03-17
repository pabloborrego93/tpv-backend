package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;

import com.pbg.tpvbackend.dto.kitchen.KitchenProductDto;
import com.pbg.tpvbackend.model.kitchen.KitchenProduct;

@Mapper(componentModel = "spring")
public interface KitchenMapper {

	public KitchenProductDto asDto(KitchenProduct kitchenProduct);
	
}
