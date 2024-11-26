package com.formular.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {

    @NotNull
    private final String korisnickoIme;
    @NotNull
    private final String lozinka;
}
