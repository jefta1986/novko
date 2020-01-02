package com.novko.security;

public enum ApplicationRoles {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String role;

    ApplicationRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
