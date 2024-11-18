package com.bewym.demo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenGenerator {
    private static final String SECRET_KEY = "your_secret_key";

    public static String generateToken(String phoneNumber) {
        return Jwts.builder()
                .setSubject(phoneNumber)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 天过期
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
