package com.pbg.tpvbackend.dto.user;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class UserBasicInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5541883545546216606L;

	@Getter @Setter
	private String username;
	
	@Getter @Setter
	private String email;

	public UserBasicInfoDto() {
		super();
	}

	public UserBasicInfoDto(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}
	
}
