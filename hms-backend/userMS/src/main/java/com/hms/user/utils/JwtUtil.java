package com.hms.user.utils;

import com.hms.user.security.CustomUserDetailService;
import com.hms.user.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.access_token_expiration}")
    private String JWT_ACCESS_TOKEN_VALIDITY;

    @Value("${jwt.refresh_token_expiration}")
    private String JWT_REFRESH_TOKEN_VALIDITY;

    @Autowired
    private CustomUserDetailService userDetailService;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public Map<String, String> generateAccessAndRefreshToken(CustomUserDetails customUserDetails) {
        String accessToken = generateAccessToken(customUserDetails);
        String refreshToken = generateRefreshToken(customUserDetails);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        return tokens;
    }

    public String generateAccessToken(CustomUserDetails customUserDetails) {
        Map<String, Object> claims = setClaims(customUserDetails);
        return doGenerateToken(claims, customUserDetails.getEmail(), JWT_ACCESS_TOKEN_VALIDITY);
    }

    public String generateRefreshToken(CustomUserDetails customUserDetails) {
        Map<String, Object> claims = setClaims(customUserDetails);
        return doGenerateToken(claims, customUserDetails.getEmail(), JWT_REFRESH_TOKEN_VALIDITY);
    }

    public String generateAccessTokenWithRefreshToken(String token) {
        Claims claims = getClaims(token);
        return doGenerateToken(claims, claims.getSubject(), JWT_ACCESS_TOKEN_VALIDITY);
    }

    private static Map<String, Object> setClaims(CustomUserDetails customUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", customUserDetails.getId());
        claims.put("username", customUserDetails.getUsername());
        claims.put("email", customUserDetails.getEmail());
        claims.put("role", customUserDetails.getRole());
        claims.put("profileId", customUserDetails.getProfileId());
        return claims;
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, String expiration) {
        return Jwts
                .builder()
                .claims(claims)
                .issuedAt(new Date())
                .subject(subject)
                .expiration(new Date(System.currentTimeMillis()
                        + Long.parseLong(expiration)))
                .signWith(secretKey)
                .compact();
    }

    public boolean isTokenValid(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        UserDetails userDetails = userDetailService.loadUserByUsername(claims.getSubject());
        return (userDetails != null && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaims(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (JwtException e) {
            throw new JwtException("Error while checking JWT token ", e);
        }
    }

    public Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException ex) {
            claims = ex.getClaims();
        }
        return claims;
    }


}
