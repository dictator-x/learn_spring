package com.learn_spring_parent.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.joda.time.DateTime;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    private static final String JWT_PAYLOAD_USR_KEY = "user";

    @Data
    public static class PayLoad<T> {
        private String id;
        private T userInfo;
        private Date expiration;
    }

    public static String genToken(Object userInfo, PrivateKey privateKey, int expireInSecond) {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USR_KEY, JsonUtil.mustMarshalString(userInfo))
                .setId(createJTI())
                .setExpiration(DateTime.now().plusSeconds(expireInSecond).toDate())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    private static String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    public static<T> PayLoad<T> getPayLoadFromToken(String token, PublicKey publicKey, Class<T> userType) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        PayLoad<T> claims = new PayLoad<>();
        claims.setId(body.getId());
        claims.setUserInfo(JsonUtil.unmarshal(body.get(JWT_PAYLOAD_USR_KEY).toString(), userType));
        claims.setExpiration(body.getExpiration());
        return claims;
    }
}
