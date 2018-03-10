package com.pbg.tpvbackend.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class UserPostDto {

	@Getter @Setter
	private String username;
	@Getter @Setter
	private String email;
	@Getter @Setter
	private String password;
	
	public UserPostDto() {
		super();
	}

	public UserPostDto(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
}
