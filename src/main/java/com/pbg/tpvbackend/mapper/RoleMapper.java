package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.pbg.tpvbackend.dto.role.RoleDto;
import com.pbg.tpvbackend.model.security.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

	@Mappings({ 
        @Mapping(source = "name", target = "name")
    })
	public RoleDto asUserExtendedInfoDto(Role role);
	
}
