package com.formular.repository;

import com.formular.model.Formular;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularRepository extends JpaRepository<Formular, Long> {

    @Query("SELECT f.naziv, k.korisnickoIme, f.vremePoslednjeIzmene, p " +
            "FROM Formular f " +
            "LEFT JOIN f.polja p " +
            "JOIN f.korisnikKreirao k")
    Page<Object[]> findAllFormularsWithPolja(Pageable pageable);


}
