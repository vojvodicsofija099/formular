package com.formular.authorization;

import com.formular.model.Korisnik;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Builder
public class KorisnickiDetalji implements UserDetails {

    private final Korisnik korisnik;

    public KorisnickiDetalji(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Koristi rolu korisnika kao GrantedAuthority
        return Collections.singletonList(new SimpleGrantedAuthority(korisnik.getRola().name()));
    }

    @Override
    public String getPassword() {
        return korisnik.getLozinka();
    }

    @Override
    public String getUsername() {
        return korisnik.getKorisnickoIme();
    }

}
