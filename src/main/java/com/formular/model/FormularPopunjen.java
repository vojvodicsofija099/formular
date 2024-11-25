package com.formular.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "formular_popunjen")
@Getter
public class FormularPopunjen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_formular", nullable = false)
    private Formular formular;

    @Column(name = "vreme_kreiranja", nullable = false, updatable = false)
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    private LocalDateTime vremePoslednjeIzmene;

}
