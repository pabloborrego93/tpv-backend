package com.pbg.tpvbackend.mapper;

import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainDto;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainPostDto;
import com.pbg.tpvbackend.model.RestaurantChain;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-03-11T17:06:17+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 9.0.4 (Oracle Corporation)"
)
@Component
public class RestaurantChainMapperImpl implements RestaurantChainMapper {

    @Override
    public RestaurantChainDto asRestaurantChainDto(RestaurantChain restaurantChain) {
        if ( restaurantChain == null ) {
            return null;
        }

        RestaurantChainDto restaurantChainDto = new RestaurantChainDto();

        return restaurantChainDto;
    }

    @Override
    public RestaurantChain asRestaurantChain(RestaurantChainPostDto restaurantChainPostDto) {
        if ( restaurantChainPostDto == null ) {
            return null;
        }

        RestaurantChain restaurantChain = new RestaurantChain();

        return restaurantChain;
    }
}
