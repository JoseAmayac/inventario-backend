package com.stock.inventario.auth.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.stock.inventario.users.dto.BasicUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class JwtService {

    public String generateToken(String payload){
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000 * 2);
        Algorithm algorithm = Algorithm.HMAC256("jojasdofjasdfahsdfiohasdofhasiodhfioshadfioasdhfoiashoi");
        return JWT.create()
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withIssuer(payload)
                .sign(algorithm);
    }

    /**
    public Authentication validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256("jojasdofjasdfahsdfiohasdofhasiodhfioshadfioasdhfoiashoi");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);
        BasicUserDTO user = this.authService.getById(decoded.getIssuer());

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }*/


}
