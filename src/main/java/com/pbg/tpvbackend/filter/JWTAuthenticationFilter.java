package com.pbg.tpvbackend.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbg.tpvbackend.config.ConfigProperties;
import com.pbg.tpvbackend.dto.user.UserLoginDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		try {
			UserLoginDto creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginDto.class);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>());
			return authenticationManager.authenticate(auth);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, ConfigProperties.getExpiration_time());
		
		String token = Jwts.builder()
			.setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
			.setExpiration(calendar.getTime())
			.signWith(SignatureAlgorithm.HS512, ConfigProperties.getSecret().getBytes()).compact();
		res.addHeader(ConfigProperties.getHEADER_STRING(), ConfigProperties.getTOKEN_PREFIX() + token);
		
	}

}
