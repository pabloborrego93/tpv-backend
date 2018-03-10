package com.pbg.tpvbackend.mapper;

import com.pbg.tpvbackend.dto.restaurant.RestaurantDto;
import com.pbg.tpvbackend.model.Restaurant;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-03-10T18:39:44+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class RestaurantMapperImpl implements RestaurantMapper {

    @Override
    public RestaurantDto asRestaurantDto(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantDto restaurantDto = new RestaurantDto();

        return restaurantDto;
    }
}
