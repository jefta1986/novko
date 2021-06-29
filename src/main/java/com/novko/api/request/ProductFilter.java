package com.novko.api.request;

public class ProductFilter implements Filter {

    private Boolean active;
    private String namePart;
    private String namePartSr;
    private String codePart;

    public ProductFilter() {}

    public ProductFilter(Boolean active, String namePart, String namePartSr, String codePart) {
        this.active = active;
        this.namePart = namePart;
        this.namePartSr = namePartSr;
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

    public String getNamePartSr() {
        return namePartSr;
    }

    public void setNamePartSr(String namePartSr) {
        this.namePartSr = namePartSr;
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
