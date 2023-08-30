package com.expertsoft.phoneshop;

public class PhoneShopConstants {

    public static final String ROOT_PATH = "/";
    public static final String PHONES_PATH = "/phones";
    public static final String ADMIN_PATH = "/admin";
    public static final String LOGIN_PATH = "/login";
    public static final String LOGIN_FAILURE_PATH = "/login?error=true";
    public static final String LOGOUT_PATH = "/logout";
    public static final String PHONES_ADD_PATH = "/phones/**";

    public static final String LOGIN = "login";
    public static final String NAME = "name";
    public static final String BIO = "bio";
    public static final String AVATAR_URL = "avatarUrl";
    public static final String LOCATION = "location";
    public static final String COMPANY = "company";

    public static final String ADMIN = "ADMIN";

    private PhoneShopConstants() {
        // instance not allowed
    }
}
