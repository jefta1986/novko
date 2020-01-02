package com.novko.security;

import org.springframework.security.core.GrantedAuthority;

public class Roles implements GrantedAuthority {

    private static final long serialVersionUID = 3193602481768488160L;

    private String role;

    public Roles(){}

    public Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String getAuthority() {
        return this.role;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "role='" + role + '\'' +
                '}';
    }
}
