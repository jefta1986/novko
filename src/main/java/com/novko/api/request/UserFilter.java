package com.novko.api.request;

public class UserFilter implements Filter {

    private Boolean active;
    private String emailPart;
    private String mbPart;
    private String pibPart;


    public UserFilter() {}

    public UserFilter(Boolean active, String emailPart, String mbPart, String pibPart) {
        this.active = active;
        this.emailPart = emailPart;
        this.mbPart = mbPart;
        this.pibPart = pibPart;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmailPart() {
        return emailPart;
    }

    public void setEmailPart(String emailPart) {
        this.emailPart = emailPart;
    }

    public String getMbPart() {
        return mbPart;
    }

    public void setMbPart(String mbPart) {
        this.mbPart = mbPart;
    }

    public String getPibPart() {
        return pibPart;
    }

    public void setPibPart(String pibPart) {
        this.pibPart = pibPart;
    }
}
