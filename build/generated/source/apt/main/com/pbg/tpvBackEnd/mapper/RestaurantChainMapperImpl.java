package com.pbg.tpvBackEnd.mapper;

import com.pbg.tpvBackEnd.dto.RestaurantChainDto;

import com.pbg.tpvBackEnd.dto.RestaurantChainPostDto;

import com.pbg.tpvBackEnd.dto.RestaurantDto;

import com.pbg.tpvBackEnd.model.Restaurant;

import com.pbg.tpvBackEnd.model.RestaurantChain;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2017-12-03T12:37:22+0100",

    comments = "version: 1.2.0.CR1, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"

)

@Component

public class RestaurantChainMapperImpl implements RestaurantChainMapper {

    @Autowired

    private RestaurantMapper restaurantMapper;

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

    protected List<RestaurantDto> restaurantListToRestaurantDtoList(List<Restaurant> list) {

        if ( list == null ) {

            return null;
        }

        List<RestaurantDto> list1 = new ArrayList<RestaurantDto>( list.size() );

        for ( Restaurant restaurant : list ) {

            list1.add( restaurantMapper.asRestaurantDto( restaurant ) );
        }

        return list1;
    }
}

