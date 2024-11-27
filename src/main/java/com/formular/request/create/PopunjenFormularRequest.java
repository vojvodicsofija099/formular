package com.formular.request.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PopunjenFormularRequest {

    @NotNull
    private final Long formularId;

    List<PopunjenoPoljeRequest> popunjenaPolja;
}
