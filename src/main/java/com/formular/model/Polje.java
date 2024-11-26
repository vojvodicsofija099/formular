package com.formular.model;

import com.formular.exception.FormularManagementException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "polje")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_korisnik_kreirao", nullable = false)
    private Korisnik korisnikKreirao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_korisnik_poslednji_azurirao")
    private Korisnik korisnikPoslednjiAzurirao;

    public enum TipPolja {
        TEKST, BROJ;

        public static TipPolja fromString(String tip) {
            try {
                return TipPolja.valueOf(tip);
            } catch (IllegalArgumentException e) {
                throw new FormularManagementException("Nevalidan tip polja: " + tip);
            }
        }
    }
}
