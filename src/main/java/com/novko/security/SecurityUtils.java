package com.novko.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;



public class SecurityUtils {
	
	private SecurityUtils() {
		super();
	}
	

	public static User getUserFromContext() {

		SecurityContext securityContext = SecurityContextHolder.getContext();
		Object details = null;

		try {
			details = securityContext.getAuthentication().getPrincipal();
		} catch (NullPointerException e) {
			details = null;
		}

		User user = null;
		if (details != null && details instanceof User) {
			user = (User) details;
		}

		return user;
	}
	

}
