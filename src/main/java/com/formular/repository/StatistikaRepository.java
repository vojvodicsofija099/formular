package com.formular.repository;

import com.formular.model.Statistika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatistikaRepository extends JpaRepository<Statistika, Long> {
}
