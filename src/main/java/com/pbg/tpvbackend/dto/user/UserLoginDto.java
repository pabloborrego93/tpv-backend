package com.pbg.tpvbackend.dto.user;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class UserLoginDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7323976815594311405L;

	@Getter @Setter
	private String username;
	
	@Getter @Setter
	private String password;
	
	@Getter @Setter
	private String chainId;
	
	public UserLoginDto() {
		super();
	}

	public UserLoginDto(String username, String password, String chainId) {
		super();
		this.username = username;
		this.password = password;
		this.chainId = chainId;
	}

}
