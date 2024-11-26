package com.formular.service;

import com.formular.authorization.KorisnickiDetalji;
import com.formular.model.Korisnik;
import com.formular.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class KorisnikServis implements UserDetailsService {

    private final KorisnikRepository korisnikRepository;

    @Autowired
    public KorisnikServis(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }


    @Override
    public KorisnickiDetalji loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(username)
                .orElseThrow(() -> new UsernameNotFoundException("Korisnik nije pronađen: " + username));
        return new KorisnickiDetalji(korisnik);
    }
}
