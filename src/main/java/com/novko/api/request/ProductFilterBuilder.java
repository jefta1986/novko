package com.novko.api.request;

public class ProductFilterBuilder {
    private Boolean active;
    private String namePart;
    private String codePart;

    public ProductFilterBuilder setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public ProductFilterBuilder setNamePart(String namePart) {
        this.namePart = namePart;
        return this;
    }

    public ProductFilterBuilder setCodePart(String codePart) {
        this.codePart = codePart;
        return this;
    }

    public ProductFilter createProductFilter() {
        return new ProductFilter(active, namePart, codePart);
    }
}