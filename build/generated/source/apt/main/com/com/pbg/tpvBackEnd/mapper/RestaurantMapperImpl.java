package com.pbg.tpvBackEnd.mapper;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

import com.pbg.tpvBackEnd.dto.RestaurantDto;
import com.pbg.tpvBackEnd.mapper.RestaurantMapper;
import com.pbg.tpvBackEnd.model.Restaurant;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2017-09-24T12:36:25+0200",

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

