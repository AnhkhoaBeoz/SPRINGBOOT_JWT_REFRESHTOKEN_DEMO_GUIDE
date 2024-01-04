package com.khoabeo.demojwt.Constants;

public class JWTConstants {
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_SECRET_KEY = "7916b1fe5851b9da0b9458ad404f1104085706c03c228c8dab8482cae97f86c6";
    public static final int JWT_EXPIRATION_TIME = 604800; /// 7 day hết hạn token 604800
    public static final int JWT_REFRESH_TOKEN_EXPIRY = 1000 * 600; //REFRESH_TOKEN_EXPIRY is 10 minues


}
