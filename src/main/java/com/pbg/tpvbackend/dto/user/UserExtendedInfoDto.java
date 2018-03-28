package com.pbg.tpvbackend.dto.user;

import java.util.ArrayList;
import java.util.List;

import com.pbg.tpvbackend.dto.role.RoleDto;

import lombok.Getter;
import lombok.Setter;

public class UserExtendedInfoDto extends UserBasicInfoDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6833894654168357358L;

	@Getter @Setter
	private List<RoleDto> roles;

	public UserExtendedInfoDto() {
		super();
		roles = new ArrayList<RoleDto>();
	}

	public UserExtendedInfoDto(String username, String email, List<RoleDto> roles) {
		super(username, email);
		this.roles = roles;
	}
	
}
