package com.formular.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {

    private final String korisnickoIme;
    private final String lozinka;
}
