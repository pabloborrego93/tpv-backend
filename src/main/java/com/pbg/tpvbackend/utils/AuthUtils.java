package com.pbg.tpvbackend.utils;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.pbg.tpvbackend.model.security.RoleName;

public class AuthUtils {
	
	public static Authentication createAuthenticationForAnonymousUser() {
        List<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(RoleName.ROLE_ANONYMOUS.name());
        return new AnonymousAuthenticationToken(AppConstants.getANONYMOUS_USER(), AppConstants.getANONYMOUS_USER(), authorities);
    }
	
	public static HttpHeaders jwtHttpHeader(String jwt) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, jwt);
		return headers;
	}
	
}
