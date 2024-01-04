package com.khoabeo.demojwt.service;

import com.khoabeo.demojwt.payload.*;

public interface AuthService {

    JWTAuthResponse login(LoginUserDto loginUserDto);

    String register(RegisterUserDto registerUserDto);

    public JWTAuthResponse refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}
