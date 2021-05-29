package com.novko.api.request;

public enum ProductSortProperty {

    AMOUNT_DIN("amountDin"),
    NEWEST("createdDate");

    private final String field;

    ProductSortProperty(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
