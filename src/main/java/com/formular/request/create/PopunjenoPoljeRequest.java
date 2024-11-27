package com.formular.request.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PopunjenoPoljeRequest {

    @NotNull
    private final Long poljeId;
    @NotNull
    private final String vrednostTekst;
    @NotNull
    private final Double vrednostBroj;
}
