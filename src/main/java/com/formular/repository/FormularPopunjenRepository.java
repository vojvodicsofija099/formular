package com.formular.repository;

import com.formular.model.FormularPopunjen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FormularPopunjenRepository extends JpaRepository<FormularPopunjen, Long> {

    @Query("SELECT COUNT(f) FROM FormularPopunjen f WHERE DATE(f.vremeKreiranja) = :datum")
    Integer prebrojKreirane(@Param("datum") LocalDate datum);
}
