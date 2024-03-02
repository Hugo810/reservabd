package com.br.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.modelos.Quarto;
import com.br.modelos.StatusQuarto;
import com.br.modelos.TipoQuarto;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {
  
    @Query("SELECT q FROM Quarto q WHERE q.tipo = :tipo")
    List<Quarto> findByTipo(@Param("tipo") TipoQuarto tipo);
    @Query("SELECT q FROM Quarto q WHERE q.status = :status")
	List<Quarto> findByStatusQuarto(StatusQuarto status);
}

