package com.formular.jwt;

import com.formular.model.Korisnik;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String secretKey;

    public String generateToken(String korisnickoIme, Korisnik.RolaKorisnika rola) {
        return Jwts.builder()
                .setSubject(korisnickoIme)
                .claim("rola", rola.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //1h expiration
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Korisnik.RolaKorisnika extractRole(String token) {
        String rola =  extractClaims(token).get("rola", String.class);
        return Korisnik.RolaKorisnika.valueOf(rola);
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
        return (username != null && username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
