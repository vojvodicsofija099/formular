package com.formular.controller;

import com.formular.request.FormularRequest;
import com.formular.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface FormularManagerAPI {

    String REQUEST_MAPPING = "/api/auth";
    String LOGIN_REQUEST_MAPPING = "/login";
    String FORMULAR_REQUEST_MAPPING = "/formular";

    @PostMapping(LOGIN_REQUEST_MAPPING)
    ResponseEntity<String> login(@RequestBody LoginRequest loginRequest);

    @PostMapping(FORMULAR_REQUEST_MAPPING)
    void createFormular(@RequestBody FormularRequest formularRequest);
}
