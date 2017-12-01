package com.pbg.tpvBackEnd.mapper;

import com.pbg.tpvBackEnd.dto.UserPostDto;

import com.pbg.tpvBackEnd.model.security.User;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2017-12-01T22:09:04+0100",

    comments = "version: 1.2.0.CR1, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"

)

@Component

public class UserMapperImpl implements UserMapper {

    @Override

    public UserPostDto toDTO(User user) {

        if ( user == null ) {

            return null;
        }

        UserPostDto userPostDto = new UserPostDto();

        userPostDto.setUsername( user.getUsername() );

        userPostDto.setPassword( user.getPassword() );

        userPostDto.setEmail( user.getEmail() );

        return userPostDto;
    }

    @Override

    public User toEntity(UserPostDto UserPostDto) {

        if ( UserPostDto == null ) {

            return null;
        }

        User user = new User();

        user.setUsername( UserPostDto.getUsername() );

        user.setPassword( UserPostDto.getPassword() );

        user.setEmail( UserPostDto.getEmail() );

        return user;
    }

    @Override

    public void mapToEntity(UserPostDto userDto, User user) {

        if ( userDto == null ) {

            return;
        }

        user.setUsername( userDto.getUsername() );

        user.setPassword( userDto.getPassword() );

        user.setEmail( userDto.getEmail() );
    }
}

