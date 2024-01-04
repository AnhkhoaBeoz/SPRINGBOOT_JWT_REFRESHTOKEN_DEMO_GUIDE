package com.khoabeo.demojwt.service;

import com.khoabeo.demojwt.modal.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(String username);

    Optional<RefreshToken> findRefreshTokenByToken(String token);

    RefreshToken verifyExpiration(RefreshToken token);

    RefreshToken findRefreshTokenByUserName(String username);

    void deteleTokenByRefreshToken(RefreshToken token);


}
