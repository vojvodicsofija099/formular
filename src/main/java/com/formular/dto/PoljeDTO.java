package com.formular.dto;

import com.formular.model.Polje;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PoljeDTO {

    private String naziv;
    private Integer redosled;
    private String tip;
}
