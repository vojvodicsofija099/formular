package com.formular.controller;

import com.formular.authorization.KorisnickiDetalji;
import com.formular.dto.FormularDTO;
import com.formular.dto.FormularPopunjenDTO;
import com.formular.jwt.JwtUtil;
import com.formular.model.Korisnik;
import com.formular.request.FormularRequest;
import com.formular.request.LoginRequest;
import com.formular.request.PoljeReguest;
import com.formular.request.PopunjenFormularRequest;
import com.formular.service.FormularPopunjenService;
import com.formular.service.FormularService;
import com.formular.service.PoljeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.formular.controller.FormularManagerAPI.API;

@RestController
@RequestMapping(API)
@Transactional
public class FormularManagerController implements FormularManagerAPI {

    private final AuthenticationManager authenticationManager;
    private final FormularService formularService;
    private final FormularPopunjenService formularPopunjenService;
    private final PoljeService poljeService;
    private final JwtUtil jwtUtil;


    @Autowired
    public FormularManagerController(AuthenticationManager authenticationManager, FormularService formularService, FormularPopunjenService formularPopunjenService, PoljeService poljeService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.formularService = formularService;
        this.formularPopunjenService = formularPopunjenService;
        this.poljeService = poljeService;
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
        formularService.createFormular(formularRequest.getNaziv(), getCurrentUserDetails().getUsername());
    }

    @Override
    public Page<FormularDTO> getAllFormularsWithPagination(@RequestParam int page, @RequestParam int size) {
        return formularService.findAllFormularsWithPagination(page, size);
    }

    @Override
    public void createPolje(PoljeReguest poljeRequest) {
        poljeService.createPolje(poljeRequest.getNaziv(), poljeRequest.getIdFormulara(), poljeRequest.getRedosled(),
                poljeRequest.getTip(), getCurrentUserDetails().getUsername());
    }

    public ResponseEntity<FormularPopunjenDTO> popuniFormular(@RequestBody @Valid PopunjenFormularRequest popunjenFormularRequest) {
        FormularPopunjenDTO formularPopunjenDTO = formularPopunjenService.popuniFormular(popunjenFormularRequest.getFormularId(), getCurrentUserDetails().getUsername(), popunjenFormularRequest.getPopunjenaPolja());
        return ResponseEntity.ok(formularPopunjenDTO);
    }

    public static KorisnickiDetalji getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof KorisnickiDetalji) {
                return (KorisnickiDetalji) principal;
            }
        } else {
            throw new IllegalArgumentException("Postoji problem sa autentifikacijom");
        }
        return null;
    }

}
