package com.br.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import com.br.modelos.Quarto;
import com.br.modelos.StatusQuarto;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    List<Quarto> findByStatus(StatusQuarto status);
}
