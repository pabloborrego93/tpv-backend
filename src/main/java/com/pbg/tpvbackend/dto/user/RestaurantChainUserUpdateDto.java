package com.pbg.tpvbackend.dto.user;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pbg.tpvbackend.dto.role.RoleDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class RestaurantChainUserUpdateDto {

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
	@Size(min = 2, max = 16, message = "Firstname length must be between 2 and 16 chars")
	@NotNull(message = "Firstname can not be null")
    @NotBlank(message = "Firstname can not be blank")
	private String firstname;
	
	@Getter @Setter
	@Size(min = 2, max = 32, message = "Lastname length must be between 2 and 32 chars")
	@NotNull(message = "Lastname can not be null")
    @NotBlank(message = "Lastname can not be blank")
	private String lastname;
	
	@Getter @Setter @Builder.Default
	private Set<RoleDto> roles = new HashSet<>();
	
	public RestaurantChainUserUpdateDto() {
		super();
	}

	public RestaurantChainUserUpdateDto(
			@Size(min = 4, max = 16, message = "Username length must be between 4 and 16 chars") @NotNull(message = "Username can not be null") @NotBlank(message = "Username can not be blank") String username,
			@NotNull(message = "Email can not be null") @NotBlank(message = "Email can not be blank") @Email(message = "Email Address is not a valid format") @Size(min = 6, max = 32, message = "Email length must be between 6 and 32 chars") String email,
			@Size(min = 2, max = 16, message = "Firstname length must be between 2 and 16 chars") @NotNull(message = "Firstname can not be null") @NotBlank(message = "Firstname can not be blank") String firstname,
			@Size(min = 2, max = 32, message = "Lastname length must be between 2 and 32 chars") @NotNull(message = "Lastname can not be null") @NotBlank(message = "Lastname can not be blank") String lastname,
			Set<RoleDto> roles) {
		super();
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.roles = roles;
	}

}
