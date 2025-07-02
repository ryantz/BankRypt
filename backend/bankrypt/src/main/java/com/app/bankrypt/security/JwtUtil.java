package com.app.bankrypt.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.SerialException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;
    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException error) {
            System.err.println("JWT token is expired: " + error.getMessage());
        } catch (UnsupportedJwtException error) {
            System.err.println("JWT token is unsupported: " + error.getMessage());
        } catch (MalformedJwtException error) {
            System.err.println("JWT token is malformed: " + error.getMessage());
        } catch (SecurityException error) {
            System.err.println("Invalid JWT signature: " + error.getMessage());
        } catch (IllegalArgumentException error) {
            System.err.println("JWT claims string is empty: " + error.getMessage());
        } catch (JwtException error) {
            System.err.println("JWT validation failed: " + error.getMessage());
        } catch (Exception error) {
            System.err.println("Unexpected error during JWT validation" + error.getMessage());
        }
        return false;
    }
}

