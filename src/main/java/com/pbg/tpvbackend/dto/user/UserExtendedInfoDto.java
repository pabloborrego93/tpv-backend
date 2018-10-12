package com.pbg.tpvbackend.dto.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pbg.tpvbackend.dto.role.RoleDto;

import lombok.Getter;
import lombok.Setter;

public class UserExtendedInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6833894654168357358L;

	@Getter @Setter
	private String username;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String firstname;
	
	@Getter @Setter
	private String lastname;
	
	@Getter @Setter
	private List<RoleDto> roles;

	public UserExtendedInfoDto() {
		super();
		roles = new ArrayList<RoleDto>();
	}

	public UserExtendedInfoDto(String username, String email, String firstname, String lastname, List<RoleDto> roles) {
		super();
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.roles = roles;
	}

}
