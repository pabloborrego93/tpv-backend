package com.pbg.tpvBackEnd.controller.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvBackEnd.jwt.JwtTokenUtil;

@RestController
@RequestMapping("protected")
public class MethodProtectedRestController {

	@Value("${jwt.header}")
    private String tokenHeader;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProtectedGreeting(HttpServletRequest request) {
    	String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        
        logger.info(
        		"El token del usuario: " 
        				+username+ 
        		" expirara detro de: " 
        				+ (jwtTokenUtil
        						.getExpirationDateFromToken(token)
        						.getTime() - 
        						(new Date().getTime()))/60/10 + " segundos");
        
        return ResponseEntity.ok(200);
    }

}