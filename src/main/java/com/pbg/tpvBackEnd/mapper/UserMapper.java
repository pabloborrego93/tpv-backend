package com.pbg.tpvBackEnd.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.pbg.tpvBackEnd.dto.UserPostDto;
import com.pbg.tpvBackEnd.model.security.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	
	public UserPostDto toDTO(User user);

	public User toEntity(UserPostDto UserPostDto);

	public void mapToEntity(UserPostDto userDto, @MappingTarget User user);
	
}