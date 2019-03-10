package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;

import com.pbg.tpvbackend.dto.order.OrderLineDto;
import com.pbg.tpvbackend.model.order.OrderLine;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {

	public OrderLineDto asOrderLineDto(OrderLine orderLine);
	
}
