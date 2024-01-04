package com.khoabeo.demojwt.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JWTAuthResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    public JWTAuthResponse(String accessToken, String token) {
        this.accessToken = accessToken;
        this.refreshToken = token;
    }
}
