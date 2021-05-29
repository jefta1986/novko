package com.novko.api.request;

public enum OrderSortProperty {

    NEWEST("orderDate");

    private final String field;

    OrderSortProperty(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
