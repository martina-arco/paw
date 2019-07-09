package ar.edu.itba.webapp.auth;

/**
 * Source: https://dev.to/keysh/spring-security-with-jwt-3j76
 */
public final class AuthConstants {
    public static final String AUTH_LOGIN_URL = "/api/v1/login";

    public static final String JWT_SECRET = "aPdSgVkYp3s5v8y/B?E(H+MbQeThWmZq4t7w9z$C&F)J@NcRfUjXn2r5u8x/A%D*";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "paw-2018a-6-api";
    public static final String TOKEN_AUDIENCE = "paw-2018a-6-app";
}