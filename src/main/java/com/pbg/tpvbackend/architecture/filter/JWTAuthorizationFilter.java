package com.pbg.tpvbackend.architecture.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.mysql.jdbc.StringUtils;
import com.pbg.tpvbackend.exception.InvalidJWTException;
import com.pbg.tpvbackend.service.utils.AuthUtilsService;
import com.pbg.tpvbackend.utils.AppConstants;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	@Autowired
	private AuthUtilsService authUtilsService;
	
	@Autowired
    private HandlerExceptionResolver resolver;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(AppConstants.getHEADER_STRING());

		if (StringUtils.isNullOrEmpty(header) || !header.startsWith(AppConstants.getTOKEN_PREFIX())) {
			onUnsuccessfulAuthentication(req, res, new InvalidJWTException(AppConstants.getERR_JWT_EMPTY()));
			return;
		}

		Object authentication = null;
		try {
			authentication = authUtilsService.getAuthentication(req);
		} catch (InvalidJWTException failed) {
			onUnsuccessfulAuthentication(req, res, failed);
			return;
		}
		SecurityContextHolder.getContext().setAuthentication((Authentication) authentication);
		chain.doFilter(req, res);
	}

	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
		resolver.resolveException(request, response, null, failed);
	}

}
