package com.formular.request.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CreatePoljeReguest {

    @NotNull
    private final String naziv;
    @NotNull
    private final Long idFormulara;
    @NotNull
    private final int redosled;
    @NotNull
    private final String tip;
}
