package com.pbg.tpvbackend.service.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;
import com.pbg.tpvbackend.architecture.config.AppProperties;
import com.pbg.tpvbackend.model.security.CustomUserDetails;
import com.pbg.tpvbackend.model.security.User;
import com.pbg.tpvbackend.utils.AppConstants;
import com.pbg.tpvbackend.utils.AuthUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthUtilsServiceImpl implements AuthUtilsService {

	private AppProperties appProperties;
	
	public Date getExpTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, appProperties.getJwt().getExpirationTime());
		return calendar.getTime();
	}
	
	public Object getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(AppConstants.getHEADER_STRING());
		
		if (!StringUtils.isNullOrEmpty(token)) {
			List<String> roles = new ArrayList<String>();
			Claims claims = null;
			Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
			try {
				claims = Jwts
					.parser()
					.setSigningKey(appProperties.getJwt().getSecret().getBytes())
					.parseClaimsJws(token.replace(AppConstants.getTOKEN_PREFIX(), ""))
					.getBody();
				
				roles = (List<String>) claims.getOrDefault(AppConstants.getJWT_ROLES(), Lists.newArrayList());
				authorities = roles.stream()
				        .map(role -> new SimpleGrantedAuthority(role.toString()))
				        .collect(Collectors.toSet());
				
				
				
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
			}

			if (!StringUtils.isNullOrEmpty(claims.getSubject())) {
				CustomUserDetails customUserDetails = new CustomUserDetails(claims);
				return new UsernamePasswordAuthenticationToken(claims.getSubject(), customUserDetails, authorities);
			}
		}
		return AuthUtils.createAuthenticationForAnonymousUser();
	}
	
}
