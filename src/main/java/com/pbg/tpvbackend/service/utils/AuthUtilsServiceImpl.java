package com.pbg.tpvbackend.service.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;
import com.pbg.tpvbackend.architecture.config.AppProperties;
import com.pbg.tpvbackend.exception.InvalidJWTException;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.model.security.CustomUserDetails;
import com.pbg.tpvbackend.model.security.User;
import com.pbg.tpvbackend.service.UserService;
import com.pbg.tpvbackend.utils.AppConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthUtilsServiceImpl implements AuthUtilsService {

	private static final Logger logger = LogManager.getLogger(AuthUtilsServiceImpl.class);
	
	private AppProperties appProperties;
	private UserService userService;
	
	public Date getExpTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, appProperties.getJwt().getExpirationTime());
		return calendar.getTime();
	}
	
	public Object getAuthentication(HttpServletRequest request) throws InvalidJWTException {
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
				String mensajeError = String.format(AppConstants.getERR_JWT_EXPIRED());
				throw new InvalidJWTException(mensajeError);
			} catch (Exception e) {
				String mensajeError = String.format(AppConstants.getERR_JWT_INVALID());
				throw new InvalidJWTException(mensajeError);
			}

			if (!StringUtils.isNullOrEmpty(claims.getSubject())) {
				CustomUserDetails customUserDetails = new CustomUserDetails(claims);
				return new UsernamePasswordAuthenticationToken(claims.getSubject(), customUserDetails, authorities);
			}
		}
		String mensajeError = String.format(AppConstants.getERR_JWT_INVALID());
		throw new InvalidJWTException(mensajeError);
	}
	
	public String generateTokenFromBDInfo() throws UserNotFoundException {
		Map<String, Object> claims = new HashMap<>();
		User user = userService.findByUsername();
		
		// Roles
		List<String> roles = user.getRoles().stream().map(r -> r.getName().toString()).collect(Collectors.toList());
		claims.put(AppConstants.getJWT_ROLES(), roles);
		// Name
		claims.put(AppConstants.getJWT_FIRSTNAME(), user.getFirstname());
		// Surname
		claims.put(AppConstants.getJWT_LASTNAME(), user.getLastname());
		// Email
		claims.put(AppConstants.getJWT_EMAIL(), user.getEmail());
		// ChainId
		claims.put(AppConstants.getJWT_CHAIN_ID(), user.getChain().getId());
		// UserId
		claims.put(AppConstants.getJWT_USER_ID(), user.getId());
		
		return Jwts.builder()
			.setSubject(user.getUsername())
			.setExpiration(this.getExpTime())
			.setIssuedAt(Calendar.getInstance().getTime())
			.addClaims(claims)
			.signWith(SignatureAlgorithm.HS512, appProperties.getJwt().getSecret().getBytes()).compact();
	}
	
}
