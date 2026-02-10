package com.hms.user.utils;

import com.hms.user.exceptions.HmsException;
import com.hms.user.security.CustomUserDetails;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
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

    @Value("${jwt.expiration}")
    private String JWT_TOKEN_VALIDITY;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

   public String generateToken( CustomUserDetails customUserDetails)  {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", customUserDetails.getId());
        claims.put("username", customUserDetails.getUsername());
        claims.put("email", customUserDetails.getEmail());
        claims.put("role", customUserDetails.getRole());
        return doGenerateToken(claims, customUserDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject)  {
       return Jwts
                    .builder()
                    .claims(claims)
                    .issuedAt(new Date())
                    .subject(subject)
                    .expiration(new Date(System.currentTimeMillis()
                            + Long.parseLong(JWT_TOKEN_VALIDITY)))
                    .signWith(secretKey)
                    .compact();
    }


}
