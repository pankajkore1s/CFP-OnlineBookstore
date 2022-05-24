package com.BridgeLabz.BookstoreApp.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Component;
@Component
public class TokenUtility {

    private static final String TOKEN = "Pankaj";

    public String createToken(int id) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN);

            String token = JWT.create()
                    .withClaim("user_id", id)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
            //log Token Signing Failed
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}