package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;

import com.pbg.tpvbackend.dto.order.OrderDto;
import com.pbg.tpvbackend.model.order.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

	public OrderDto asOrderDto(Order order);
	
}
