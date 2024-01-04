package com.khoabeo.demojwt.jwt;

import com.khoabeo.demojwt.Constants.JWTConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class JwtService {
    // Generate JWT token

    public String generateToken(String username) {
        // Extract username from authentication
//        String username = authentication.getName();

        // Get the current date
        Date currentDate = new Date();

        // Calculate the expiration date based on the current date and defined expiration time
        Date expireDate = new Date(currentDate.getTime() + JWTConstants.JWT_EXPIRATION_TIME);

        // Build the JWT token
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expireDate)
                .setIssuedAt(new Date())
                .signWith(getSignInKey())
                .compact();
        return token;
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWTConstants.JWT_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    // Extract username from JWT token
    public String getUsername(String token) {
        // Decode the token
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(JWTConstants.JWT_SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        // Extract and return the username from the token's claims
        String userName = claims.getSubject();
        return userName;
    }

    // Validate the integrity and expiration of the JWT token
    public static boolean validateToken(String token) {
        try {
            // Parse and verify the token's signature and claims
            Jwts.parserBuilder()
                    .setSigningKey(JWTConstants.JWT_SECRET_KEY)

                    .build()
                    .parseClaimsJws(token);

            // Token is valid; additional checks (e.g., expiration time) can be added here
            return true;
        } catch (Exception e) {
            // Authentication failed; the token is invalid
            return false;
        }
    }
}
