package com.pbg.tpvbackend.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.mysql.jdbc.StringUtils;
import com.pbg.tpvbackend.architecture.config.ConfigProperties;
import com.pbg.tpvbackend.model.security.RoleName;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

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
	
	public static Object getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(ConfigProperties.getHEADER_STRING());
		
		if (!StringUtils.isNullOrEmpty(token)) {
			String user = new String();
			
			try {
				user = Jwts
					.parser()
					.setSigningKey(ConfigProperties.getSecret().getBytes())
					.parseClaimsJws(token.replace(ConfigProperties.getTOKEN_PREFIX(), ""))
					.getBody()
					.getSubject();
				
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
			}

			if (!StringUtils.isNullOrEmpty(user)) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
		}
		return AuthUtils.createAuthenticationForAnonymousUser();
	}
	
}
