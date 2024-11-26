package com.formular.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "korisnik")
@Getter
@Setter
@NoArgsConstructor
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "korisnicko_ime", nullable = false, unique = true)
    private String korisnickoIme;

    @Column(name = "lozinka", nullable = false)
    private String lozinka;

    @Enumerated(EnumType.STRING)
    @Column(name = "rola", nullable = false)
    private RolaKorisnika rola;

    @Column(name = "vreme_kreiranja", nullable = false, updatable = false)
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    private LocalDateTime vremePoslednjeIzmene;

    public Korisnik(String korisnickoIme, String lozinka, RolaKorisnika rola) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.rola = rola;
        this.vremeKreiranja = LocalDateTime.now();
        this.vremePoslednjeIzmene = LocalDateTime.now();
    }

    public enum RolaKorisnika {
        ADMIN, RADNIK
    }

    @PreUpdate
    public void updateTimestamp() {
        this.vremePoslednjeIzmene = LocalDateTime.now();
    }
}