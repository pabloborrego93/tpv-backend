package com.pbg.tpvBackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pbg.tpvBackEnd.filter.Http403ForbiddenEntryPoint;
import com.pbg.tpvBackEnd.filter.JWTAuthenticationFilter;
import com.pbg.tpvBackEnd.filter.JWTLoginFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	Http403ForbiddenEntryPoint authenticationEntryPoint;
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable().authorizeRequests()
	        .antMatchers(HttpMethod.POST, "/login").permitAll()
	        .anyRequest().authenticated()
	        .and()
	    	.formLogin().disable()
	    	.httpBasic().authenticationEntryPoint(authenticationEntryPoint)
	    	.and()
	        // We filter the api/login requests
	        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
	        // And filter other requests to check the presence of JWT in header
	        .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	  }

	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    // Create a default account
	    auth.inMemoryAuthentication()
	        .withUser("admin")
	        .password("password")
	        .roles("ADMIN");
	  }
	
}
