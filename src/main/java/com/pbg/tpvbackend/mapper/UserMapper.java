package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.pbg.tpvbackend.dto.user.RestaurantChainUserPostDto;
import com.pbg.tpvbackend.dto.user.RestaurantChainUserUpdateDto;
import com.pbg.tpvbackend.dto.user.UserBasicInfoDto;
import com.pbg.tpvbackend.dto.user.UserExtendedInfoDto;
import com.pbg.tpvbackend.dto.user.UserPostDto;
import com.pbg.tpvbackend.model.security.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mappings({ 
        @Mapping(source = "username", target = "username"),
        @Mapping(source = "email", target = "email"),
        @Mapping(source = "firstname", target = "firstname"),
        @Mapping(source = "lastname", target = "lastname")
    })
	public User asEntity(UserPostDto UserPostDto);
	
	public User asEntity(RestaurantChainUserPostDto restaurantChainUserPostDto);
	
	public User asEntity(RestaurantChainUserUpdateDto restaurantChainUserUpdateDto);
	
	@Mappings({ 
        @Mapping(source = "username", target = "username"),
        @Mapping(source = "email", target = "email")
    })
	public UserBasicInfoDto asUserBasicInfoDto(User user);
	
	@Mappings({ 
        @Mapping(source = "username", target = "username"),
        @Mapping(source = "email", target = "email"),
        @Mapping(source = "roles", target = "roles")
    })
	public UserExtendedInfoDto asUserExtendedInfoDto(User user);
	
}