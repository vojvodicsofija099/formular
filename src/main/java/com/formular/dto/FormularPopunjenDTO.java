package com.formular.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FormularPopunjenDTO {

    private String nazivFormulara;
    private String korisnikKreirao;
    private LocalDateTime vremeKreiranja;
    private List<PoljePopunjenoDTO> popunjenaPolja;
}
