package io.reflectoring.humsafar.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    private final String SECRET = "my-secret-key";

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    private boolean validateToken(String token) {
        try {
            String tokenEmail = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
            //return true
            return true;
        } catch (Exception e) {
            return false; // or handle the exception as per your application's requirements
        }
    }
}