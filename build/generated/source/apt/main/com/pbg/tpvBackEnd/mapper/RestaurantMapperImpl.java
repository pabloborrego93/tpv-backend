package com.pbg.tpvBackEnd.mapper;

import com.pbg.tpvBackEnd.dto.RestaurantDto;

import com.pbg.tpvBackEnd.model.Restaurant;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2017-12-03T12:37:22+0100",

    comments = "version: 1.2.0.CR1, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"

)

@Component

public class RestaurantMapperImpl implements RestaurantMapper {

    @Override

    public RestaurantDto asRestaurantDto(Restaurant restaurant) {

        if ( restaurant == null ) {

            return null;
        }

        RestaurantDto restaurantDto = new RestaurantDto();

        restaurantDto.setName( restaurant.getName() );

        restaurantDto.setAddress( restaurant.getAddress() );

        return restaurantDto;
    }
}

