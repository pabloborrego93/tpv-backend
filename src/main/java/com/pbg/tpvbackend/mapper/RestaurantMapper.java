package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.pbg.tpvbackend.dto.restaurant.RestaurantDto;
import com.pbg.tpvbackend.dto.restaurant.RestaurantPostDto;
import com.pbg.tpvbackend.model.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

	@Mappings({ 
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "address", target = "address")
    })
    public RestaurantDto asRestaurantDto(Restaurant restaurant);
	
	@Mappings({ 
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "address", target = "address")
    })
    public Restaurant restaurantPostDtoAsRestaurant(RestaurantPostDto restaurantPostDto);

}
