package com.formular.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "statistika")
@Getter
public class Statistika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datum", nullable = false)
    private LocalDate datum;

    @Column(name = "broj_popunjenih_formulara", nullable = false)
    private Integer brojPopunjenihFormulara;

}
