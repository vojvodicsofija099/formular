package com.formular.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "polje_popunjeno")
@Getter
@Builder
@AllArgsConstructor
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
    @Setter
    private String vrednostTekst;

    @Column(name = "vrednost_broj")
    @Setter
    private Double vrednostBroj;

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
}
