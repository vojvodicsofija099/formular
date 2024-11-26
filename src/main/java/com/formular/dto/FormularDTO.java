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
public class FormularDTO {

    String naziv;
    String korisnik;
    LocalDateTime izmenjen;
    List<PoljeDTO> polja;
}
