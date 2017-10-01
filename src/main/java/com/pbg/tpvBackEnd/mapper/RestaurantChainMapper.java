package com.pbg.tpvBackEnd.mapper;

import org.mapstruct.Mapper;

import com.pbg.tpvBackEnd.dto.RestaurantChainDto;
import com.pbg.tpvBackEnd.dto.RestaurantChainPostDto;
import com.pbg.tpvBackEnd.model.RestaurantChain;

@Mapper(uses = { RestaurantChain.class }, componentModel = "spring")
public interface RestaurantChainMapper {

    RestaurantChainDto asRestaurantChainDto(RestaurantChain restaurantChain);
    RestaurantChain asRestaurantChain(RestaurantChainPostDto restaurantChainPostDto);

}
