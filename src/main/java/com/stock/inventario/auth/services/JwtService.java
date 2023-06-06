package com.stock.inventario.auth.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private final String JWT_SECRET = "jsdpfjoasdjfopasjdfpuufofjpopjfasd.-fadfkakpfasdfpasdf@odsfopasofpa-asdfjafo";

    public String generateToken(String userId){
        Instant now = Instant.now();
        Duration validityDuration = Duration.ofHours( 6 );
        Instant validity = now.plus( validityDuration );
        Date validityDate = Date.from( validity );

        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        Map<String, String> payload = new HashMap<>();
        payload.put("user", userId);
        return JWT.create()
                .withIssuedAt(now)
                .withExpiresAt(validityDate)
                .withPayload(payload)
                .sign(algorithm);
    }

    public String validateToken(String token) throws JWTVerificationException{
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJwt = verifier.verify(token);
        return decodedJwt.getClaim("user").asString();
    }

}
