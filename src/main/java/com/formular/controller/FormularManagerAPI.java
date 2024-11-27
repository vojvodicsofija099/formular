package com.formular.controller;

import com.formular.dto.FormularDTO;
import com.formular.dto.FormularPopunjenDTO;
import com.formular.dto.PoljeDTO;
import com.formular.request.create.FormularRequest;
import com.formular.request.create.LoginRequest;
import com.formular.request.create.CreatePoljeReguest;
import com.formular.request.create.PopunjenFormularRequest;
import com.formular.request.update.UpdatePoljeRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FormularManagerAPI {

    String API = "/api";
    String LOGIN_REQUEST_MAPPING = "/auth/login";
    String ADMIN = "/admin";
    String FORMULARI = "/formulari";
    String POLJA = "/polja";
    String POLJA_FORMULARA = "/poljaFormulara";
    String ID = "/{id}";
    String FORMULAR_ID = "/{formularId}";
    String FORMULAR_REQUEST_MAPPING = ADMIN + FORMULARI;
    String POLJE_REQUEST_MAPPING = ADMIN + POLJA;
    String POLJE_ID_REQUEST_MAPPING = ADMIN + POLJA + ID;
    String COMMON = "/common";
    String COMMON_FORMULAR_REQUEST_MAPPING = COMMON + FORMULARI;
    String COMMON_POLJE_REQUEST_MAPPING = COMMON + POLJA;
    String COMMON_POLJE_ID_REQUEST_MAPPING = COMMON + POLJA + ID;
    String COMMON_POLJE_FORMULAR_ID_REQUEST_MAPPING = COMMON + POLJA_FORMULARA + FORMULAR_ID;
    String COMMON_POPUNJEN_FORMULAR_REQUEST_MAPPING = COMMON + "/popunjeniFormulari";

    @PostMapping(LOGIN_REQUEST_MAPPING)
    ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest);


    //formular
    @PostMapping(FORMULAR_REQUEST_MAPPING)
    @ResponseStatus(HttpStatus.CREATED)
    void createFormular(@Valid @RequestBody FormularRequest formularRequest);

    @GetMapping(COMMON_FORMULAR_REQUEST_MAPPING)
    Page<FormularDTO> getAllFormularsWithPagination(@RequestParam int page, @RequestParam int size);

    //polje
    @PostMapping(POLJE_REQUEST_MAPPING)
    @ResponseStatus(HttpStatus.CREATED)
    void createPolje(@Valid @RequestBody CreatePoljeReguest poljeRequest);
    @PutMapping(POLJE_REQUEST_MAPPING)
    void updatePolje(@Valid @RequestBody UpdatePoljeRequest poljeRequest);
    @DeleteMapping(POLJE_ID_REQUEST_MAPPING)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePolje(@PathVariable Long id);

    @GetMapping(COMMON_POLJE_ID_REQUEST_MAPPING)
    PoljeDTO getPoljeById(@PathVariable Long id);
    @GetMapping(COMMON_POLJE_REQUEST_MAPPING)
    List<PoljeDTO> getAllPolja();

    @GetMapping(COMMON_POLJE_FORMULAR_ID_REQUEST_MAPPING)
    List<PoljeDTO> getPoljaForFormularId(@PathVariable Long formularId);

    @PostMapping(COMMON_POPUNJEN_FORMULAR_REQUEST_MAPPING)
    ResponseEntity<FormularPopunjenDTO> popuniFormular(PopunjenFormularRequest popunjenFormularRequest);
}
