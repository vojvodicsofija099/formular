package com.formular.service;

import com.formular.dto.FormularPopunjenDTO;
import com.formular.dto.PoljePopunjenoDTO;
import com.formular.exception.FormularManagementException;
import com.formular.model.*;
import com.formular.repository.*;
import com.formular.request.create.PopunjenoPoljeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormularPopunjenService {


    private FormularPopunjenRepository formularPopunjenRepository;
    private PoljePopunjenoRepository poljePopunjenoRepository;
    private FormularRepository formularRepository;
    private KorisnikRepository korisnikRepository;
    private PoljeRepository poljeRepository;

    @Autowired
    public FormularPopunjenService(FormularPopunjenRepository formularPopunjenRepository, PoljePopunjenoRepository poljePopunjenoRepository,
                                   FormularRepository formularRepository, KorisnikRepository korisnikRepository, PoljeRepository poljeRepository) {
        this.formularPopunjenRepository = formularPopunjenRepository;
        this.poljePopunjenoRepository = poljePopunjenoRepository;
        this.formularRepository = formularRepository;
        this.korisnikRepository = korisnikRepository;
        this.poljeRepository = poljeRepository;
    }

    public FormularPopunjenDTO popuniFormular(Long formularId, String korisnickoIme, List<PopunjenoPoljeRequest> popunjenaPolja) {

        Formular formular = formularRepository.findById(formularId).orElseThrow(() -> new RuntimeException("Formular nije pronadjen"));
        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(korisnickoIme).orElseThrow(() -> new RuntimeException("Korisnik nije pronadjen"));

        FormularPopunjen formularPopunjen = FormularPopunjen.builder()
                .formular(formular)
                .korisnikKreirao(korisnik)
                .vremeKreiranja(LocalDateTime.now())
                .vremePoslednjeIzmene(LocalDateTime.now())
                .build();

        List<PoljePopunjeno> polja = new ArrayList<>();
        for (PopunjenoPoljeRequest poljeRequest : popunjenaPolja) {
            Polje polje = poljeRepository.findById(poljeRequest.getPoljeId()).orElseThrow(() -> new RuntimeException("Polje nije pronadjeno"));

            if(polje.getFormular() != formular) {
                throw new FormularManagementException("Polje "+polje.getNaziv()+" ne odgovara formularu "+formular.getNaziv());
            }

            PoljePopunjeno poljePopunjeno = PoljePopunjeno.builder()
                    .formularPopunjen(formularPopunjen)
                    .polje(polje)
                    .vremeKreiranja(LocalDateTime.now())
                    .vremePoslednjeIzmene(LocalDateTime.now())
                    .korisnikKreirao(korisnik)
                    .build();

            switch (polje.getTip()) {
                case TEKST -> poljePopunjeno.setVrednostTekst(poljeRequest.getVrednostTekst());
                case BROJ -> poljePopunjeno.setVrednostBroj(poljeRequest.getVrednostBroj());
                default -> throw new IllegalArgumentException("Nepoznat tip polja");
            }

            polja.add(poljePopunjeno);
        }

        formularPopunjen.setPopunjenaPolja(polja);
        formularPopunjenRepository.save(formularPopunjen);

        return new FormularPopunjenDTO(formular.getNaziv(), korisnik.getKorisnickoIme(), formularPopunjen.getVremeKreiranja(),
                polja.stream().map(p -> PoljePopunjenoDTO.builder().nazivPolja(p.getPolje().getNaziv()).vrednostBroj(p.getVrednostBroj()).vrednostTekst(p.getVrednostTekst()).build()).collect(Collectors.toList()));
    }

    /*public List<FormularPopunjenDTO> getPopunjeniFormulari(Long korisnikId) {
        List<FormularPopunjen> formulari = formularPopunjenRepository.findByKorisnikKreirao(korisnikId);
        return formulari.stream()
                .map(formular -> new FormularPopunjenDTO(formular.getFormular().getNaziv(),
                        formular.getKorisnikKreirao().getIme(),
                        formular.getVremeKreiranja(),
                        poljaPopunjena(formular)))
                .collect(Collectors.toList());
    }*/

    /*private List<PoljePopunjenoDTO> poljaPopunjena(FormularPopunjen formularPopunjen) {
        return formularPopunjen.getPopunjenaPolja().stream()
                .map(polje -> new PoljePopunjenoDTO(polje.getPolje().getNaziv(),
                        polje.getVrednostTekst(),
                        polje.getVrednostBroj()))
                .collect(Collectors.toList());
    }*/
}
