package com.formular.service;

import com.formular.authorization.KorisnickiDetalji;
import com.formular.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationService {

    private final JwtUtil jwtUtil;
    private final KorisnikServis korisnikServis;

    public JwtAuthenticationService(JwtUtil jwtUtil, KorisnikServis korisnikServis) {
        this.jwtUtil = jwtUtil;
        this.korisnikServis = korisnikServis;
    }

    public String setUpAuthentication(String token, HttpServletRequest request) {

        String korisnickoIme = jwtUtil.extractUsername(token);
        KorisnickiDetalji detalji = korisnikServis.loadUserByUsername(korisnickoIme);

        if(jwtUtil.validateToken(token, korisnickoIme)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(detalji, null, detalji.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        return korisnickoIme;
    }

}
