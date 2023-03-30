package com.khaydev.videostream.app.security;

public class EndpointConfig {

    public static final String[] ENDPOINT_WHITELIST = {
            "/api/auth/login",
            "/api/auth/register",
    };

    public static final String[] GET_METHOD_USERS_WHITELIST = {
            "/api/users/search/**",
            "/api/users/*/videos",
    };

    public static final String[] PUT_METHOD_USERS_WHITELIST = {
            "/api/users/*"
    };

    public static final String[] ADMIN_WHITELIST = {
            "/api/admin/**",

    };
}
