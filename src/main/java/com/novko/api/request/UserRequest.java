package com.novko.api.request;


public class UserRequest {

//    private Long id;
    private String username;
    private String password;
//    private String language;
    private String code;
//    private boolean active;
//    private String role;
    private Double rabat;
    private String firma;
    private String ulica;
    private String grad;
    private String pib;
    private String mb;
//    private List<Roles> roles;
//    private List<OrderRequest> orders;


    public UserRequest() {}

    public UserRequest(String username, String password, String code, Double rabat, String firma, String ulica, String grad, String pib, String mb) {
        this.username = username;
        this.password = password;
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
}
