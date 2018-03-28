package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;

import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainDto;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainPostDto;
import com.pbg.tpvbackend.model.RestaurantChain;

@Mapper(uses = { RestaurantMapper.class }, componentModel = "spring")
public interface RestaurantChainMapper {

    public RestaurantChainDto asRestaurantChainDto(RestaurantChain restaurantChain);
    public RestaurantChain asRestaurantChain(RestaurantChainPostDto restaurantChainPostDto);

}

