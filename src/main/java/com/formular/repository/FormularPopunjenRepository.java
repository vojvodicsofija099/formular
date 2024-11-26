package com.formular.repository;

import com.formular.model.FormularPopunjen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularPopunjenRepository extends JpaRepository<FormularPopunjen, Long> {
}
