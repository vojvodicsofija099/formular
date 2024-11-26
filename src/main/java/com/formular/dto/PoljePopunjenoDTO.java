package com.formular.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PoljePopunjenoDTO {

    private Long poljeId;
    private String nazivPolja;
    private String vrednostTekst;
    private Double vrednostBroj;
}
