package com.formular.service;

import com.formular.authorization.KorisnickiDetalji;
import com.formular.jwt.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationService {

    private final JwtUtil jwtUtil;
    private final KorisnikServis korisnikServis;

    public JwtAuthenticationService(JwtUtil jwtUtil, KorisnikServis korisnikServis) {
        this.jwtUtil = jwtUtil;
        this.korisnikServis = korisnikServis;
    }

    public void setUpAuthentication(String token) {

        String korisnickoIme = jwtUtil.extractUsername(token);
        KorisnickiDetalji detalji = (KorisnickiDetalji) korisnikServis.loadUserByUsername(korisnickoIme);

        if(jwtUtil.validateToken(token, korisnickoIme)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(korisnickoIme, null, detalji.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

}
