package com.formular.service;

import com.formular.model.Statistika;
import com.formular.repository.FormularPopunjenRepository;
import com.formular.repository.StatistikaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class StatisticaService {

    private final FormularPopunjenRepository formularPopunjenRepository;
    private final StatistikaRepository statistikaRepository;

    public StatisticaService(FormularPopunjenRepository formularPopunjenRepository, StatistikaRepository statistikaRepository) {
        this.formularPopunjenRepository = formularPopunjenRepository;
        this.statistikaRepository = statistikaRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Svakog dana u ponoć
    @Transactional
    public void prebrojUnose() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        Integer brojFormulara = formularPopunjenRepository.prebrojKreirane(yesterday);

        statistikaRepository.save(Statistika.builder()
                .datum(yesterday)
                .brojPopunjenihFormulara(brojFormulara)
                .build());
    }
}
