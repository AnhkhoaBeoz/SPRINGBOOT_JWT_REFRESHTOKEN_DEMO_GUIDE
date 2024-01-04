package com.khoabeo.demojwt.controller;

import com.khoabeo.demojwt.Constants.AppConstants;
import com.khoabeo.demojwt.payload.*;
import com.khoabeo.demojwt.repository.UserEntityRepository;
import com.khoabeo.demojwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginUserDto loginUserDto) {
        JWTAuthResponse jwtAuthResponse = this.authService.login(loginUserDto);

        return ResponseEntity.ok()
                .body(jwtAuthResponse);
    }



    @PostMapping("/refreshToken")
    public ResponseEntity<JWTAuthResponse> refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        JWTAuthResponse jwtAuthResponse = this.authService.refreshToken(refreshTokenRequestDTO);

        return ResponseEntity.ok()
                .body(jwtAuthResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterUserDto registerUserDto) {
        String messages = this.authService.register(registerUserDto);

        return ResponseEntity.ok()
                .body(messages);
    }

}