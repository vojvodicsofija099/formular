package com.formular.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FormularRequest {

    @NotNull
    private final String naziv;
}
