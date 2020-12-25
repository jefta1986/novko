package com.novko.api.response;

public class UserResponse {

    private Long id;
    private String username;
//    private String password;
    private String language;
    private String code;
    private boolean active;
    private String role;
    private Double rabat;
    private String firma;
    private String ulica;
    private String grad;
    private String pib;
    private String mb;
//    private List<Roles> roles;


    public UserResponse() {}

    public UserResponse(Long id, String username, String language, String code, boolean active, String role, Double rabat, String firma, String ulica, String grad, String pib, String mb) {
        this.id = id;
        this.username = username;
        this.language = language;
        this.code = code;
        this.active = active;
        this.role = role;
        this.rabat = rabat;
        this.firma = firma;
        this.ulica = ulica;
        this.grad = grad;
        this.pib = pib;
        this.mb = mb;
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

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

}
