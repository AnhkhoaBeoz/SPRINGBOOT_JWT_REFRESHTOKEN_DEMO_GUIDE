package com.khoabeo.demojwt.repository;

import com.khoabeo.demojwt.modal.RefreshToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findRefreshTokenByToken(String token);

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.userEntity.username = :username")
    RefreshToken findRefreshTokenByUserName(String username);


}