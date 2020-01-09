package com.novko.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class AccessDeniedExceptionHandler implements AccessDeniedHandler  {

	
    /* (non-Javadoc)
     * @see org.springframework.security.web.access.AccessDeniedHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.access.AccessDeniedException)
     */
	@Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) {

		try {
			response.getWriter().write("AccessDeniedException!");
			response.setStatus(403);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}