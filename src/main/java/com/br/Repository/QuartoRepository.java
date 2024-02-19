package com.br.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.modelos.Quarto;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {

}
