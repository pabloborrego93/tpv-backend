package com.pbg.tpvBackEnd.mapper;

import org.mapstruct.Mapper;

import com.pbg.tpvBackEnd.dto.RestaurantDto;
import com.pbg.tpvBackEnd.model.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantDto asRestaurantDto(Restaurant restaurant);

}
