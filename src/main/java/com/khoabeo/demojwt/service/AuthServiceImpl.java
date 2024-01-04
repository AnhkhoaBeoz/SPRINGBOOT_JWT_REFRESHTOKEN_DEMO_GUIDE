package com.khoabeo.demojwt.service;

import com.khoabeo.demojwt.jwt.JwtService;
import com.khoabeo.demojwt.modal.RefreshToken;
import com.khoabeo.demojwt.modal.Role;
import com.khoabeo.demojwt.modal.UserEntity;
import com.khoabeo.demojwt.payload.*;
import com.khoabeo.demojwt.repository.RoleRepository;
import com.khoabeo.demojwt.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private RefreshTokenService refreshTokenService;
    private UserEntityRepository userEntityRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService, RefreshTokenService refreshTokenService, UserEntityRepository userEntityRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.userEntityRepository = userEntityRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public JWTAuthResponse login(LoginUserDto loginUserDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getUsername(),
                        loginUserDto.getPassword()
                )
        );
        RefreshToken getRefreshTokenByUsername = this.refreshTokenService.findRefreshTokenByUserName(authentication.getName());

        if (getRefreshTokenByUsername != null) {
            this.refreshTokenService.deteleTokenByRefreshToken(getRefreshTokenByUsername);
        }


        if (authentication.isAuthenticated()) {


            RefreshToken refreshToken = this.refreshTokenService.createRefreshToken(loginUserDto.getUsername());

            String jwtToken = this.jwtService.generateToken(authentication.getName());

            JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
            jwtAuthResponse.setAccessToken(jwtToken);
            jwtAuthResponse.setRefreshToken(refreshToken.getToken());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return jwtAuthResponse;
        }
        return null;
    }

    @Override
    public JWTAuthResponse refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return refreshTokenService.findRefreshTokenByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserEntity)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo.getUsername());
                    return new JWTAuthResponse(accessToken, refreshTokenRequestDTO.getToken());
                })
                .orElseThrow(() -> new RuntimeException("Refresh Token is not in DB..!!"));

    }

    @Override
    public String register(RegisterUserDto registerUserDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(registerUserDto.getPassword());
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findRoleByRoleName("ROLE_USER");
        roles.add(userRole);
        user.setListRole(roles);

        this.userEntityRepository.save(user);
        return "User registered successfully!!!!.";
    }

}
