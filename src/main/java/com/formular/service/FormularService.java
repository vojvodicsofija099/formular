package com.formular.service;

import com.formular.dto.FormularDTO;
import com.formular.dto.PoljeDTO;
import com.formular.model.Formular;
import com.formular.model.Korisnik;
import com.formular.model.Polje;
import com.formular.repository.FormularRepository;
import com.formular.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FormularService {

    private final FormularRepository formularRepository;
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public FormularService(FormularRepository formularRepository, KorisnikRepository korisnikRepository) {
        this.formularRepository = formularRepository;
        this.korisnikRepository = korisnikRepository;
    }

    public Formular findFormularById(Long id) {
        return formularRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formular sa ID-om " + id + " nije pronađen"));
    }

    public void createFormular(String naziv, String korisnickoIme) {

        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(korisnickoIme)
                .orElseThrow(() -> new IllegalArgumentException("Korisnik nije pronađen"));

        Formular formular = Formular.builder()
                .naziv(naziv)
                .vremeKreiranja(LocalDateTime.now())
                .vremePoslednjeIzmene(LocalDateTime.now())
                .korisnikKreirao(korisnik)
                .build();
        formularRepository.save(formular);
    }

    /*public void updateFormular(Long id, String naziv, String korisnickoIme) {

        Formular formular = formularRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formular sa ID-om " + id + " nije pronađen"));

        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(korisnickoIme)
                .orElseThrow(() -> new IllegalArgumentException("Korisnik nije pronađen"));

        formular.setNaziv(naziv);
        formular.setVremePoslednjeIzmene(LocalDateTime.now());
        formular.setKorisnikPoslednjiAzurirao(korisnik);
        formularRepository.save(formular);
    }*/

    public void deleteFormular(Long id) {
        Formular formular = formularRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formular sa ID-om " + id + " nije pronađen"));

        formularRepository.delete(formular);
    }

    public Page<FormularDTO> findAllFormularsWithPagination(int page, int size) {

        Page<Object[]> resultPage = formularRepository.findAllFormularsWithPolja(PageRequest.of(page, size));

        return resultPage.map(row -> {
            FormularDTO formularDTO = FormularDTO.builder()
                    .naziv((String) row[0])
                    .korisnik((String) row[1])
                    .izmenjen((LocalDateTime) row[2])
                    .build();

            Polje polje = (Polje) row[3];
            List<PoljeDTO> poljaList = new ArrayList<>();
            if (polje != null) {
                PoljeDTO poljeDTO = PoljeDTO.builder()
                        .naziv(polje.getNaziv())
                        .redosled(polje.getPrikazniRedosled())
                        .tip(polje.getTip().name())
                        .build();
                poljaList.add(poljeDTO);
            }

            formularDTO.setPolja(poljaList);

            return formularDTO;
        });
    }

}
