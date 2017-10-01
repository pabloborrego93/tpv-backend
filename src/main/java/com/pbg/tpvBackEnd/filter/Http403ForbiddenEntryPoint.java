package com.pbg.tpvBackEnd.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class Http403ForbiddenEntryPoint implements AuthenticationEntryPoint {

    /**
     * Always returns a 403 error code to the client.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException,
            ServletException {

        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
    }
}