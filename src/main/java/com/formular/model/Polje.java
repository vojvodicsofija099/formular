package com.formular.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
@Entity
@Table(name = "polje")
@Getter
public class Polje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_formular", nullable = false)
    private Formular formular;

    @Column(name = "naziv", nullable = false)
    private String naziv;

    @Column(name = "prikazi_redosled", nullable = false)
    private Integer prikaziRedosled;

    @Enumerated(EnumType.STRING)
    @Column(name = "tip", nullable = false)
    private TipPolja tip;

    @Column(name = "vreme_kreiranja", nullable = false, updatable = false)
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    private LocalDateTime vremePoslednjeIzmene;

    public enum TipPolja {
        TEKST, BROJ
    }
}