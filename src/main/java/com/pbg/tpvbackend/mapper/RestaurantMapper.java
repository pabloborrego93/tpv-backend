package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;

import com.pbg.tpvbackend.dto.restaurant.RestaurantDto;
import com.pbg.tpvbackend.model.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantDto asRestaurantDto(Restaurant restaurant);

}
