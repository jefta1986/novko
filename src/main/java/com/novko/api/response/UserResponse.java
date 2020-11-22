package com.novko.api.response;

public class UserResponse {

    private Long id;
    private String username;
    private String password;
    private String language;
    private String code;
    private boolean active;
    private String role;
    private Double rabat;
//    private List<Roles> roles;


    public UserResponse() {}

    public UserResponse(Long id, String username, String password, String language, String code, boolean active, String role, Double rabat) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.language = language;
        this.code = code;
        this.active = active;
        this.role = role;
        this.rabat = rabat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getRabat() {
        return rabat;
    }

    public void setRabat(Double rabat) {
        this.rabat = rabat;
    }

}
