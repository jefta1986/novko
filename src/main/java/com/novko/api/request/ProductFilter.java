package com.novko.api.request;

public class ProductFilter implements Filter {

    private Boolean active;
    private String namePart;
    private String codePart;

    public ProductFilter() {}

    public ProductFilter(boolean active, String namePart, String codePart) {
        this.active = active;
        this.namePart = namePart;
        this.codePart = codePart;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getNamePart() {
        return namePart;
    }

    public void setNamePart(String namePart) {
        this.namePart = namePart;
    }

    public String getCodePart() {
        return codePart;
    }

    public void setCodePart(String codePart) {
        this.codePart = codePart;
    }

}
