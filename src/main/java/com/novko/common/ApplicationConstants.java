package com.novko.common;

public final class ApplicationConstants {

    //SUBJECT ZA MEJL
    public static final String SUBJECT_ORDER_SERBIAN_EMAIL = "Porudžbina Green Land";
    public static final String SUBJECT_ORDER_ENGLISH_EMAIL = "Reciept Green Land";


    //MEJL ZA ORDER (sa PDF fakturom)
    public static final String ORDER_SERBIAN_EMAIL = "Poštovani %s,\n\nUspešno ste izvršili porudžbinu.\nU prilogu Vam dostavljamo fakturu za plaćanje.\n\nSrdačan pozdrav,\nGreen Land Solutions doo\n+381 61 228 0 228";
    public static final String ORDER_ENGLISH_EMAIL = "Dear %s, you are ordered products";


    //MEJL ZA NOVOG (REGISTROVANOG) USERA
    public static final String SUBJECT_USER_REGISTRATION_SERBIAN_EMAIL = "Dobro došli na Green Land sajt za kupovinu";
    public static final String SUBJECT_USER_REGISTRATION_ENGLISH_EMAIL = "Welcome to Green Land e shopping";

    public static final String USER_REGISTRATION_SERBIAN_EMAIL = "Poštovani %s, ulogovani ste...";
    public static final String USER_REGISTRATION_ENGLISH_EMAIL = "Dear %s,\nThank you for registering with Green Land.\nPlease use the following credentials to log in and edit your personal information including your own password.\nUsername: %s\nPassword: %s\nThank you,\nGreen Land";


    private ApplicationConstants() {
    }
}
