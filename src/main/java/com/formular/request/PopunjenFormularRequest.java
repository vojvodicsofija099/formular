package com.formular.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Getter
@AllArgsConstructor
public class PopunjenFormularRequest {

    @NotNull
    private final Long formularId;

    List<PopunjenoPoljeRequest> popunjenaPolja;
}
