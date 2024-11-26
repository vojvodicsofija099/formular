package com.formular.service;

import com.formular.model.Formular;
import com.formular.model.Korisnik;
import com.formular.model.Polje;
import com.formular.repository.FormularRepository;
import com.formular.repository.KorisnikRepository;
import com.formular.repository.PoljeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PoljeService {

    private final PoljeRepository poljeRepository;

    private final KorisnikRepository korisnikRepository;
    private final FormularRepository formularRepository;

    @Autowired
    public PoljeService(PoljeRepository poljeRepository, KorisnikRepository korisnikRepository, FormularRepository formularRepository) {
        this.poljeRepository = poljeRepository;
        this.korisnikRepository = korisnikRepository;
        this.formularRepository = formularRepository;
    }

    public void createPolje(String naziv, Long idFormulara, int redosled, String tip, String korisnickoIme) {

        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(korisnickoIme)
                .orElseThrow(() -> new IllegalArgumentException("Korisnik nije pronađen"));
        Formular formular = formularRepository.findById(idFormulara)
                .orElseThrow(() -> new IllegalArgumentException("Formular nije pronađen"));
        Polje.TipPolja tipPolja = Polje.TipPolja.fromString(tip);

        Polje polje = Polje.builder()
                .naziv(naziv)
                .formular(formular)
                .prikaziRedosled(redosled)
                .tip(tipPolja)
                .vremeKreiranja(LocalDateTime.now())
                .vremePoslednjeIzmene(LocalDateTime.now())
                .korisnikKreirao(korisnik)
                .build();

        poljeRepository.save(polje);
    }
}
