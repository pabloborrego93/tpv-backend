package com.pbg.tpvbackend.architecture.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbg.tpvbackend.architecture.config.AppProperties;
import com.pbg.tpvbackend.dto.user.UserLoginDto;
import com.pbg.tpvbackend.model.security.CustomUserDetails;
import com.pbg.tpvbackend.service.utils.AuthUtilsService;
import com.pbg.tpvbackend.utils.AppConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private AppProperties appProperties;
	@Autowired
	private AuthUtilsService authUtilsService;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		try {
			UserLoginDto creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginDto.class);
			String username = creds.getUsername();
			/* if(!StringUtils.isEmpty(creds.getChainId())) {
				username = com.pbg.tpvbackend.utils.StringUtils.generateUserPrefix(Integer.parseInt(creds.getChainId()), creds.getUsername());
			} else {
				username = creds.getUsername();
			} */
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, creds.getPassword(), new ArrayList<>());
			return getAuthenticationManager().authenticate(auth);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
		
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		
		Map<String, Object> claims = new HashMap<String, Object>();
		
		// Roles
		List<String> roles = userDetails.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toList());
		claims.put(AppConstants.getJWT_ROLES(), roles);
		// Name
		claims.put(AppConstants.getJWT_FIRSTNAME(), userDetails.getFirstname());
		// Surname
		claims.put(AppConstants.getJWT_LASTNAME(), userDetails.getLastname());
		// Email
		claims.put(AppConstants.getJWT_EMAIL(), userDetails.getEmail());
		// ChainId
		claims.put(AppConstants.getJWT_CHAIN_ID(), userDetails.getChainId());
		// UserId
		claims.put(AppConstants.getJWT_USER_ID(), userDetails.getId());
		
		String token = Jwts.builder()
			.setSubject(userDetails.getUsername())
			.setExpiration(authUtilsService.getExpTime())
			.setIssuedAt(Calendar.getInstance().getTime())
			.addClaims(claims)
			.signWith(SignatureAlgorithm.HS512, appProperties.getJwt().getSecret().getBytes()).compact();
		res.addHeader(AppConstants.getHEADER_STRING(), AppConstants.getTOKEN_PREFIX() + token);
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();
		out.println("{");
        out.println("\"access_token\": \""+token+"\"");
        out.println("}");
        out.close();
	}

}
