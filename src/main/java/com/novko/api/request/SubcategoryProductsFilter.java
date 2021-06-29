package com.novko.api.request;

public class SubcategoryProductsFilter implements Filter {

    private Boolean active;
    private String subcategoryName;
    private String productNamePart;
    private String productNamePartSr;
    private String productCodePart;

    public SubcategoryProductsFilter() {}

    public SubcategoryProductsFilter(Boolean active, String subcategoryName, String productNamePart, String productNamePartSr, String productCodePart) {
        this.active = active;
        this.subcategoryName = subcategoryName;
        this.productNamePart = productNamePart;
        this.productNamePartSr = productNamePartSr;
        this.productCodePart = productCodePart;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public String getProductNamePart() {
        return productNamePart;
    }

    public void setProductNamePart(String productNamePart) {
        this.productNamePart = productNamePart;
    }

    public String getProductNamePartSr() {
        return productNamePartSr;
    }

    public void setProductNamePartSr(String productNamePartSr) {
        this.productNamePartSr = productNamePartSr;
    }

    public String getProductCodePart() {
        return productCodePart;
    }

    public void setProductCodePart(String productCodePart) {
        this.productCodePart = productCodePart;
    }
}
