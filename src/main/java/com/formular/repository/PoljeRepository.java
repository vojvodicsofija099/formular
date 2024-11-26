package com.formular.repository;

import com.formular.model.Polje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoljeRepository extends JpaRepository<Polje, Long> {
}
