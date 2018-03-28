package com.pbg.tpvbackend.dto.role;

import java.io.Serializable;

import com.pbg.tpvbackend.model.security.RoleName;

public class RoleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5876671224355167057L;
	
	private RoleName name;

	public RoleDto() {
		super();
	}

	public RoleDto(RoleName name) {
		super();
		this.name = name;
	}

	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}
	
}
