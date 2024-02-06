package com.example.springbootjwtsecurityjava4.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${secret.key}")
    private String key;

    public String generateToken(String username) {
        return JWT.create()
                .withIssuer("daniel_tamoe")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(ZonedDateTime.now().plusMonths(1).toInstant()))
                .withSubject("User details")
                .sign(Algorithm.HMAC512(key));
    }

    public String validation(String jwt) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(key))
                .withIssuer("daniel_tamoe")
                .withSubject("User details")
                .build();
        DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
        return decodedJWT.getClaim("username").asString();
    }
}