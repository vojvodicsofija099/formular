package com.formular.controller;

import com.formular.dto.FormularDTO;
import com.formular.dto.FormularPopunjenDTO;
import com.formular.model.Formular;
import com.formular.request.FormularRequest;
import com.formular.request.LoginRequest;
import com.formular.request.PoljeReguest;
import com.formular.request.PopunjenFormularRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

public interface FormularManagerAPI {

    String API = "/api";
    String LOGIN_REQUEST_MAPPING = "/auth/login";
    String ADMIN = "/admin";
    String FORMULARI = "/formulari";
    String POLJA = "/polja";
    String FORMULAR_REQUEST_MAPPING = ADMIN + FORMULARI;
    String POLJE_REQUEST_MAPPING = ADMIN + POLJA;
    String COMMON = "/common";
    String COMMON_FORMULAR_REQUEST_MAPPING = COMMON + FORMULARI;
    String COMMON_POLJE_REQUEST_MAPPING = COMMON + POLJA;
    String COMMON_POPUNJEN_FORMULAR_REQUEST_MAPPING = COMMON + "/popunjeniFormulari";

    @PostMapping(LOGIN_REQUEST_MAPPING)
    ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest);


    //formular
    @PostMapping(FORMULAR_REQUEST_MAPPING)
    void createFormular(@Valid @RequestBody FormularRequest formularRequest);

    @GetMapping(COMMON_FORMULAR_REQUEST_MAPPING)
    Page<FormularDTO> getAllFormularsWithPagination(@RequestParam int page, @RequestParam int size);

    //polje
    @PostMapping(POLJE_REQUEST_MAPPING)
    void createPolje(@Valid @RequestBody PoljeReguest poljeRequest);

    @PostMapping(COMMON_POPUNJEN_FORMULAR_REQUEST_MAPPING)
    public ResponseEntity<FormularPopunjenDTO> popuniFormular(PopunjenFormularRequest popunjenFormularRequest);
}
