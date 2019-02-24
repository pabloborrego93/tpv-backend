package com.pbg.tpvbackend.model.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.Lists;
import com.pbg.tpvbackend.utils.AppConstants;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.Setter;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6218933552528570431L;

	@Getter
	@Setter
	private String id;
	@Setter
	private String username;
	@Setter
	private String password;
	@Getter
	@Setter
	private String firstname;
	@Getter
	@Setter
	private String lastname;
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private List<RoleName> roles = new ArrayList<RoleName>();
	@Getter
	@Setter
	private Integer chainId;

	public CustomUserDetails(User user) {
		this.id = user.getId().toString();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		if(user.getChain() != null) {
			this.chainId = user.getChain().getId();
		}
		this.roles = Lists.newArrayList(user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()));
	}

	public CustomUserDetails(Claims claims) {
		this.id = claims.get(AppConstants.getJWT_USER_ID(), String.class);
		this.username = claims.getSubject();
		this.firstname = claims.get(AppConstants.getJWT_FIRSTNAME(), String.class);
		this.lastname = claims.get(AppConstants.getJWT_LASTNAME(), String.class);
		this.email = claims.get(AppConstants.getJWT_EMAIL(), String.class);
		List<String> rolesStr = claims.get(AppConstants.getJWT_ROLES(), List.class);
		this.roles = rolesStr.stream().map(role -> RoleName.valueOf(RoleName.class, role)).collect(Collectors.toList());
		this.chainId = (Integer) claims.get(AppConstants.getJWT_CHAIN_ID());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toSet());
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
