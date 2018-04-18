package com.pbg.tpvbackend.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.pbg.tpvbackend.architecture.config.ConfigProperties;
import com.pbg.tpvbackend.model.security.RoleName;

public class AuthUtils {

	private static final String ANONYMOUS_USER = "anonymous";
	
	public static Date getExpTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, ConfigProperties.getExpiration_time());
		return calendar.getTime();
	}
	
	public static Authentication createAuthenticationForAnonymousUser() {
        List<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(RoleName.ROLE_ANONYMOUS.name());
        return new AnonymousAuthenticationToken(ANONYMOUS_USER, ANONYMOUS_USER, authorities);
    }
	
}
