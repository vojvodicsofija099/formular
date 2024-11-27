package com.formular.repository;

import com.formular.model.Polje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoljeRepository extends JpaRepository<Polje, Long> {

    List<Polje> findByFormularId(Long formularId);
}
