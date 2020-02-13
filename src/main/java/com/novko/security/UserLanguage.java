package com.novko.security;

public enum UserLanguage {
    EN("en"),
    SR("sr");

    private String language;

    UserLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
