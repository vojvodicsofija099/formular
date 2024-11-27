package com.formular.service;

import com.formular.dto.PoljeDTO;
import com.formular.model.Formular;
import com.formular.model.Korisnik;
import com.formular.model.Polje;
import com.formular.repository.FormularRepository;
import com.formular.repository.KorisnikRepository;
import com.formular.repository.PoljeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                .prikazniRedosled(redosled)
                .tip(tipPolja)
                .vremeKreiranja(LocalDateTime.now())
                .vremePoslednjeIzmene(LocalDateTime.now())
                .korisnikKreirao(korisnik)
                .build();

        poljeRepository.save(polje);
    }

    public void updatePolje(Long id, String naziv, Long idFormulara, int redosled, String tip, String korisnickoIme) {

        Polje polje = poljeRepository.findById(id).orElseThrow(() -> new RuntimeException("Polje nije pronadjeno"));
        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(korisnickoIme)
                .orElseThrow(() -> new IllegalArgumentException("Korisnik nije pronađen"));
        Formular formular = formularRepository.findById(idFormulara)
                .orElseThrow(() -> new IllegalArgumentException("Formular nije pronađen"));
        Polje.TipPolja tipPolja = Polje.TipPolja.fromString(tip);

        polje.setNaziv(naziv);
        polje.setFormular(formular);
        polje.setPrikazniRedosled(redosled);
        polje.setTip(tipPolja);
        polje.setVremePoslednjeIzmene(LocalDateTime.now());
        polje.setKorisnikPoslednjiAzurirao(korisnik);

        poljeRepository.save(polje);
    }

    public void deletePolje(Long id) {
        if (poljeRepository.existsById(id)) {
            poljeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Polje sa id-jem " + id + " nije pronadjeno");
        }
    }

    public PoljeDTO getPoljeById(Long id) {
        Optional<Polje> optionalPolje = poljeRepository.findById(id);
        if (optionalPolje.isPresent()) {
            Polje polje = optionalPolje.get();
            return PoljeDTO.builder()
                    .naziv(polje.getNaziv())
                    .tip(polje.getTip().name())
                    .redosled(polje.getPrikazniRedosled())
                    .build();
        } else {
            throw new RuntimeException("Polje sa id-jem " + id + " nije pronadjeno");
        }
    }

    public List<PoljeDTO> getAllPolja() {
        List<PoljeDTO> poljeDTOS = new ArrayList<>();
        poljeRepository.findAll()
                .forEach(polje -> poljeDTOS.add(PoljeDTO.builder()
                        .naziv(polje.getNaziv())
                        .tip(polje.getTip().name())
                        .redosled(polje.getPrikazniRedosled())
                        .build()));
        return poljeDTOS;
    }

    public List<PoljeDTO> getPoljaForFormularId(Long formularId) {
        List<PoljeDTO> poljeDTOS = new ArrayList<>();
        poljeRepository.findByFormularId(formularId)
                .forEach(polje -> poljeDTOS.add(PoljeDTO.builder()
                        .naziv(polje.getNaziv())
                        .tip(polje.getTip().name())
                        .redosled(polje.getPrikazniRedosled())
                        .build()));
        return poljeDTOS;
    }
}
