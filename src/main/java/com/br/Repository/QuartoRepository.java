package com.br.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.modelos.Quarto;
import com.br.modelos.StatusQuarto;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
	
	List<Quarto> findByStatusQuato(StatusQuarto status);

}
