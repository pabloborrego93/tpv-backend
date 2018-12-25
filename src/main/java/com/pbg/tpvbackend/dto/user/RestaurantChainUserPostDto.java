package com.pbg.tpvbackend.dto.user;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pbg.tpvbackend.architecture.annotation.FieldMatch;
import com.pbg.tpvbackend.model.security.RoleName;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match", mustMatch = true)
public class RestaurantChainUserPostDto {

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
	
	@Getter @Setter
	@NotNull(message = "Password can not be null")
    @NotBlank(message = "Password can not be blank")
	@Size(min = 8, max = 32, message = "Password length must be between 8 and 32 chars")
	private String password;
	
	@Getter @Setter
	@NotNull(message = "Confirm password can not be null")
    @NotBlank(message = "Confirm password can not be blank")
	@Size(min = 8, max = 32, message = "Confirm password length must be between 8 and 32 chars")
	private String confirmPassword;
	
	@Getter @Setter @Builder.Default
	private Set<RoleName> roles = new HashSet<>();
	
	public RestaurantChainUserPostDto() {
		super();
	}

	public RestaurantChainUserPostDto(
			@Size(min = 4, max = 16, message = "Username length must be between 4 and 16 chars") @NotNull(message = "Username can not be null") @NotBlank(message = "Username can not be blank") String username,
			@NotNull(message = "Email can not be null") @NotBlank(message = "Email can not be blank") @Email(message = "Email Address is not a valid format") @Size(min = 6, max = 32, message = "Email length must be between 6 and 32 chars") String email,
			@Size(min = 2, max = 16, message = "Firstname length must be between 2 and 16 chars") @NotNull(message = "Firstname can not be null") @NotBlank(message = "Firstname can not be blank") String firstname,
			@Size(min = 2, max = 32, message = "Lastname length must be between 2 and 32 chars") @NotNull(message = "Lastname can not be null") @NotBlank(message = "Lastname can not be blank") String lastname,
			@NotNull(message = "Password can not be null") @NotBlank(message = "Password can not be blank") @Size(min = 8, max = 32, message = "Password length must be between 8 and 32 chars") String password,
			@NotNull(message = "Confirm password can not be null") @NotBlank(message = "Confirm password can not be blank") @Size(min = 8, max = 32, message = "Confirm password length must be between 8 and 32 chars") String confirmPassword, Set<RoleName> roles) {
		super();
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.roles = roles;
	}

}
