package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import com.pbg.tpvbackend.dto.user.UserBasicInfoDto;
import com.pbg.tpvbackend.dto.user.UserPostDto;
import com.pbg.tpvbackend.model.security.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	
	@Mappings({ 
        @Mapping(source = "username", target = "username"),
        @Mapping(source = "email", target = "email")
    })
	public User asEntity(UserPostDto UserPostDto);

	@Mappings({ 
        @Mapping(source = "username", target = "username"),
        @Mapping(source = "email", target = "email")
    })
	public UserBasicInfoDto asUserBasicInfoDto(User user);
	
}