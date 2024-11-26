package com.formular.request;

import com.formular.constants.FormularConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;


import java.util.Map;

import static com.formular.constants.FormularConstants.VALUE_NOT_NULL;

@Getter
@AllArgsConstructor
public class PoljeReguest {

    @NotNull
    private final String naziv;
    @NotNull
    private final Long idFormulara;
    @NotNull
    private final int redosled;
    @NotNull
    private final String tip;
}
