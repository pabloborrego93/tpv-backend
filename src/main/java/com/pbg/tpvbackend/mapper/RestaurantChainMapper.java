package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainDto;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainPostDto;
import com.pbg.tpvbackend.model.RestaurantChain;

@Mapper(uses = { RestaurantMapper.class }, componentModel = "spring")
public interface RestaurantChainMapper {

	@Mappings({ 
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "restaurants", target = "restaurants"),
        @Mapping(source = "families", target = "families")
    })
    public RestaurantChainDto asRestaurantChainDto(RestaurantChain restaurantChain);
    public RestaurantChain asRestaurantChain(RestaurantChainPostDto restaurantChainPostDto);

}

