package com.pbg.tpvBackEnd.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

import com.pbg.tpvBackEnd.dto.RestaurantChainDto;
import com.pbg.tpvBackEnd.dto.RestaurantChainPostDto;
import com.pbg.tpvBackEnd.dto.RestaurantDto;
import com.pbg.tpvBackEnd.mapper.RestaurantChainMapper;
import com.pbg.tpvBackEnd.model.Restaurant;
import com.pbg.tpvBackEnd.model.RestaurantChain;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2017-09-24T12:36:25+0200",

    comments = "version: 1.2.0.CR1, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"

)

@Component

public class RestaurantChainMapperImpl implements RestaurantChainMapper {

    @Override

    public RestaurantChainDto asRestaurantChainDto(RestaurantChain restaurantChain) {

        if ( restaurantChain == null ) {

            return null;
        }

        RestaurantChainDto restaurantChainDto = new RestaurantChainDto();

        restaurantChainDto.setName( restaurantChain.getName() );

        restaurantChainDto.setRestaurants( restaurantListToRestaurantDtoList( restaurantChain.getRestaurants() ) );

        return restaurantChainDto;
    }

    @Override

    public RestaurantChain asRestaurantChain(RestaurantChainPostDto restaurantChainPostDto) {

        if ( restaurantChainPostDto == null ) {

            return null;
        }

        RestaurantChain restaurantChain = new RestaurantChain();

        restaurantChain.setName( restaurantChainPostDto.getName() );

        return restaurantChain;
    }

    protected RestaurantDto restaurantToRestaurantDto(Restaurant restaurant) {

        if ( restaurant == null ) {

            return null;
        }

        RestaurantDto restaurantDto = new RestaurantDto();

        restaurantDto.setName( restaurant.getName() );

        restaurantDto.setAddress( restaurant.getAddress() );

        return restaurantDto;
    }

    protected List<RestaurantDto> restaurantListToRestaurantDtoList(List<Restaurant> list) {

        if ( list == null ) {

            return null;
        }

        List<RestaurantDto> list1 = new ArrayList<RestaurantDto>( list.size() );

        for ( Restaurant restaurant : list ) {

            list1.add( restaurantToRestaurantDto( restaurant ) );
        }

        return list1;
    }
}

