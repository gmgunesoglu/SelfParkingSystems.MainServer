package com.SelfParkingSystems.MainServer.service;


import com.SelfParkingSystems.MainServer.entity.Person;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.SECRET_KEY}")
    private String SECRET_KEY;
    @Value("${jwt.TOKEN_DURATION}")
    private Long TOKEN_DURATION;

    private Key key;

    @PostConstruct
    public void initKey() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    public String generateJwt(String userId, String userName) {
        Map<String, String> claims = Map.of("id", userId, "userName", userName);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.get("id"))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ TOKEN_DURATION))
                .signWith(key)
                .compact();
    }

    private boolean isExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    public Long getId(String jwt) {
        Claims claims = getClaims(jwt);
        String id = (String) claims.get("id");
        return Long.parseLong(id);
    }

    public String getUsername(String jwt) {
        Claims claims = getClaims(jwt);
        return (String) claims.get("userName");
    }

    public Long getId(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        return getId(token);
    }

    public String getUsername(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        return getUsername(token);
    }
}
