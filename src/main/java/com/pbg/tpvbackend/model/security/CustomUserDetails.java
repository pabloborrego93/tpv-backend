package com.pbg.tpvbackend.model.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.Lists;
import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.utils.AppConstants;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.Setter;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6218933552528570431L;

	@Setter
	private String username;
	@Setter
	private String password;
	@Getter @Setter
	private String firstname;
	@Getter @Setter
	private String lastname;
	@Getter @Setter
	private String email;
	@Getter @Setter
	private List<Restaurant> worksIn;
	@Getter @Setter
	private List<Role> roles;
	
	public CustomUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		this.worksIn = Lists.newArrayList(user.getWorksIn());
		this.roles = Lists.newArrayList(user.getRoles());
	}

	public CustomUserDetails(Claims claims) {
		this.username = claims.getSubject();
		this.firstname = claims.get(AppConstants.getJWT_FIRSTNAME(), String.class);
		this.lastname = claims.get(AppConstants.getJWT_LASTNAME(), String.class);
		this.email = claims.get(AppConstants.getJWT_EMAIL(), String.class);
		this.worksIn = claims.get(AppConstants.getJWT_WORKSIN(), List.class);
		this.roles = claims.get(AppConstants.getJWT_ROLES(), List.class);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles
	        .stream()
	        .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
	        .collect(Collectors.toSet());
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s", this.getUsername(), this.getFirstname());
	}

}
