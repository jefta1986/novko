package com.novko.security;

public enum UserLanguage {
    EN("EN"),
    SR("SR");

    private String language;

    UserLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
