package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.pbg.tpvbackend.dto.user.UserPostDto;
import com.pbg.tpvbackend.model.security.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	
	public UserPostDto toDTO(User user);

	public User toEntity(UserPostDto UserPostDto);

	public void mapToEntity(UserPostDto userDto, @MappingTarget User user);
	
}