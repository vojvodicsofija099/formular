package com.formular.repository;

import com.formular.model.PoljePopunjeno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoljePopunjenoRepository extends JpaRepository<PoljePopunjeno, Long> {
}
