package com.alura.acessible.repository;

import com.alura.acessible.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CidadeRepository extends JpaRepository<Cidade, Long> {}
