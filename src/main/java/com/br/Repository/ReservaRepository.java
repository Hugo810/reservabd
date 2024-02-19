package com.br.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.modelos.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{

}
