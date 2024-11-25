package com.formular.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "polje_popunjeno")
@Getter
public class PoljePopunjeno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_formular_popunjen", nullable = false)
    private FormularPopunjen formularPopunjen;

    @ManyToOne
    @JoinColumn(name = "id_polje", nullable = false)
    private Polje polje;

    @Column(name = "vrednost_tekst")
    private String vrednostTekst;

    @Column(name = "vrednost_broj")
    private Integer vrednostBroj;

    @Column(name = "vreme_kreiranja", nullable = false, updatable = false)
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    private LocalDateTime vremePoslednjeIzmene;
}
