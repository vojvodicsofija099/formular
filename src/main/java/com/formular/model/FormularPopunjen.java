package com.formular.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "formular_popunjen")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormularPopunjen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "formular_id", nullable = false)
    private Formular formular;

    @Column(name = "vreme_kreiranja", nullable = false, updatable = false)
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    private LocalDateTime vremePoslednjeIzmene;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_korisnik_kreirao", nullable = false)
    private Korisnik korisnikKreirao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_korisnik_poslednji_azurirao")
    private Korisnik korisnikPoslednjiAzurirao;

    @OneToMany(mappedBy = "formularPopunjen", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<PoljePopunjeno> popunjenaPolja;

}
