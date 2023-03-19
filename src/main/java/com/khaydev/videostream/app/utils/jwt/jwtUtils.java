package com.khaydev.videostream.app.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class jwtUtils {

    public String createJwt(String username, List<String> roles){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String jwt = JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() * 10 * 60 * 1000))
                .withClaim("username",username)
                .withClaim("roles",roles)
                .sign(algorithm);
        System.out.println(jwt);
        return jwt;
    }
    public String extractUsernameFromJwt(String jwt){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT theJwt = verifier.verify(jwt);
        return theJwt.getSubject();
    }
    public List<String> extractClaimFromJwt(String jwt){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT theJwt = verifier.verify(jwt);
        return theJwt.getClaim("roles").asList(String.class);
    }
}


