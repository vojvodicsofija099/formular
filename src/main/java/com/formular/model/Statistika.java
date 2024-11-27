package com.formular.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "statistika")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datum", nullable = false)
    private LocalDate datum;

    @Column(name = "broj_popunjenih_formulara", nullable = false)
    private Integer brojPopunjenihFormulara;

}
