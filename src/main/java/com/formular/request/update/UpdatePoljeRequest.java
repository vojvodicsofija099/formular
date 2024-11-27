package com.formular.request.update;

import com.formular.request.create.CreatePoljeReguest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePoljeRequest {

    @NotNull
    private final Long id;
    @NotNull
    private final String naziv;
    @NotNull
    private final Long idFormulara;
    @NotNull
    private final int redosled;
    @NotNull
    private final String tip;
}
