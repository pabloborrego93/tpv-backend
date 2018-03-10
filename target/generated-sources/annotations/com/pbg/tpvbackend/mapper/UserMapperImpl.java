package com.pbg.tpvbackend.mapper;

import com.pbg.tpvbackend.dto.user.UserPostDto;
import com.pbg.tpvbackend.model.security.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-03-10T18:39:44+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserPostDto toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserPostDto userPostDto = new UserPostDto();

        return userPostDto;
    }

    @Override
    public User toEntity(UserPostDto UserPostDto) {
        if ( UserPostDto == null ) {
            return null;
        }

        User user = new User();

        return user;
    }

    @Override
    public void mapToEntity(UserPostDto userDto, User user) {
        if ( userDto == null ) {
            return;
        }
    }
}
