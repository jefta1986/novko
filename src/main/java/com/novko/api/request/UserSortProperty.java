package com.novko.api.request;

public enum UserSortProperty {

    ID("id");
//    NEWEST("createdDate");

    private final String field;

    UserSortProperty(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
