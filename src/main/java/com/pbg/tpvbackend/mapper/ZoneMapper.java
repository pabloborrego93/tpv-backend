package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;

import com.pbg.tpvbackend.dto.zone.ZoneDto;
import com.pbg.tpvbackend.model.Zone;

@Mapper(componentModel = "spring")
public interface ZoneMapper {

	public ZoneDto asZoneDto(Zone zone);
	
}
