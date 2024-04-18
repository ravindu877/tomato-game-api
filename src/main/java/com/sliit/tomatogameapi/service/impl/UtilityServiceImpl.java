package com.sliit.tomatogameapi.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sliit.tomatogameapi.repository.UserMstRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
@RequiredArgsConstructor
@Service
public class UtilityServiceImpl {

    private final String secret = "tomato-api";

    private final UserMstRepository userMstRepository;


    protected String hidePassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    protected Boolean checkPassword(String hiddenPassword, String password){
        return BCrypt.checkpw(password, hiddenPassword);
    }

    protected String getUserTaken(String username, String password){
        Date expireDate = new Date();
        Calendar cal= Calendar.getInstance();
        cal.setTime(expireDate);
        cal.add(Calendar.DATE, 1);
        expireDate= cal.getTime();


        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create().withIssuer(secret)
                .withClaim("username", username)
                .withClaim("password", password)
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }

    public boolean requestAuthentication(String token){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(secret)
                .build();

        DecodedJWT decodedToken = verifier.verify(token);
        String username = decodedToken.getClaims().get("username").asString();

        return userMstRepository.existsByUsername(username);
    }
}
