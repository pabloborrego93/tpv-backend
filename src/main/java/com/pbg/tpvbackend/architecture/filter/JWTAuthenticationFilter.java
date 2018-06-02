package com.pbg.tpvbackend.architecture.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbg.tpvbackend.architecture.config.ConfigProperties;
import com.pbg.tpvbackend.dto.user.UserLoginDto;
import com.pbg.tpvbackend.model.security.CustomUserDetails;
import com.pbg.tpvbackend.utils.AuthUtils;

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
		
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		
		String token = Jwts.builder()
			.setSubject(userDetails.getUsername())
			.setExpiration(AuthUtils.getExpTime())
			.setIssuedAt(Calendar.getInstance().getTime())
			.signWith(SignatureAlgorithm.HS512, ConfigProperties.getSecret().getBytes()).compact();
		res.addHeader(ConfigProperties.getHEADER_STRING(), ConfigProperties.getTOKEN_PREFIX() + token);
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();
		out.println("{");
        out.println("\"access_token\": \""+token+"\"");
        out.println("}");
        out.close();
		
	}
	
}
