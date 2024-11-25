package com.formular.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;


@Entity
@Table(name = "korisnik")
@Getter
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "korisnicko_ime", nullable = false, unique = true)
    private String korisnickoIme;

    @Column(name = "lozinka", nullable = false)
    private String lozinka;

    @Column(name = "rola", nullable = false)
    private String rola;

    @Column(name = "vreme_kreiranja", nullable = false, updatable = false)
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    private LocalDateTime vremePoslednjeIzmene;

    public enum RolaKorisnika {
        ADMIN, RADNIK
    }
}
