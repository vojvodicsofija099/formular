package com.formular.controller;

import com.formular.jwt.JwtUtil;
import com.formular.model.Korisnik;
import com.formular.request.FormularRequest;
import com.formular.request.LoginRequest;
import com.formular.service.KorisnikServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.formular.controller.FormularManagerAPI.REQUEST_MAPPING;

@RestController
@RequestMapping(REQUEST_MAPPING)
public class FormularManagerController implements FormularManagerAPI {

    private final AuthenticationManager authenticationManager;
    private final KorisnikServis korisnikServis;
    private final JwtUtil jwtUtil;


    @Autowired
    public FormularManagerController(AuthenticationManager authenticationManager, KorisnikServis korisnikServis, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.korisnikServis = korisnikServis;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getKorisnickoIme(), loginRequest.getLozinka()));
        String rola = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Rola nije pronađena"));

        String token = jwtUtil.generateToken(loginRequest.getKorisnickoIme(), Korisnik.RolaKorisnika.valueOf(rola));
        return ResponseEntity.ok(token);
    }

    @Override
    public void createFormular(FormularRequest formularRequest) {

    }

}
