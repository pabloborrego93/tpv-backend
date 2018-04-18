package com.pbg.tpvbackend.architecture.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.mysql.jdbc.StringUtils;
import com.pbg.tpvbackend.architecture.config.ConfigProperties;
import com.pbg.tpvbackend.utils.AuthUtils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		String header = req.getHeader(ConfigProperties.getHEADER_STRING());

		if (header == null || !header.startsWith(ConfigProperties.getTOKEN_PREFIX())) {
			chain.doFilter(req, res);
			return;
		}

		Object authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication((Authentication) authentication);
		
		chain.doFilter(req, res);
	}

	private Object getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(ConfigProperties.getHEADER_STRING());
		
		if (StringUtils.isNullOrEmpty(token)) {
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
