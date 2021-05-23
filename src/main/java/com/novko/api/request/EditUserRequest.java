package com.novko.api.request;


public class EditUserRequest {

    private Long id;
    private String username;
    private String password;
    private String language;
    private String code;
    private Double rabat;
    private String firma;
    private String ulica;
    private String grad;
    private String pib;
    private String mb;

    public EditUserRequest() {}

    public EditUserRequest(Long id, String username, String password, String language, String code, Double rabat, String firma, String ulica, String grad, String pib, String mb) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.language = language;
        this.code = code;
        this.rabat = rabat;
        this.firma = firma;
        this.ulica = ulica;
        this.grad = grad;
        this.pib = pib;
        this.mb = mb;
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


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
