package com.br.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.modelos.Reserva;
import com.br.modelos.StatusQuarto;
import com.br.modelos.TipoQuarto;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Encontrar reserva por ID do cliente
    List<Reserva> findByClienteId(Long clienteId);

    // Encontrar reserva por ID do quarto
    List<Reserva> findByQuartoId(Long quartoId);

    // Encontrar reserva por tipo de quarto
    List<Reserva> findByQuartoTipo(TipoQuarto tipoQuarto);

    // Encontrar reserva por status de quarto
    List<Reserva> findByQuartoStatus(StatusQuarto statusQuarto);

    // Encontrar reserva por valor
    List<Reserva> findByValorReserva(double valor);
    
    List<Reserva> findByClienteNome(String nomeCliente);

    // Outros métodos personalizados aqui, se necessário...
}

