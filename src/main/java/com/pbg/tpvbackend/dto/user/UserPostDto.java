package com.pbg.tpvbackend.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class UserPostDto {

	@Getter @Setter
	@Size(min = 4, max = 16, message = "Username length must be between 4 and 16 chars")
	@NotNull(message = "Username can not be null")
    @NotBlank(message = "Username can not be blank")
	private String username;
	
	@Getter @Setter
	@NotNull(message = "Email can not be null")
    @NotBlank(message = "Email can not be blank")
	@Email(message = "Email Address is not a valid format")
	@Size(min = 6, max = 32, message = "Email length must be between 6 and 32 chars")
	private String email;
	
	@Getter @Setter
	@NotNull(message = "Password can not be null")
    @NotBlank(message = "Password can not be blank")
	@Size(min = 8, max = 32, message = "Password length must be between 8 and 32 chars")
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
