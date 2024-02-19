package com.br.Controler;

import com.br.modelos.Cliente;
import com.br.modelos.Quarto;
import com.br.modelos.Reserva;
import com.br.Exception.ResourceNotFoundException;
import com.br.Repository.ClienteRepository;
import com.br.Repository.QuartoRepository;
import com.br.Repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/reservas")
public class ReservaControler {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private QuartoRepository quartoRepository;


    // Inserir nova reserva associada a um cliente e um quarto específicos
    @PostMapping("/{clienteId}/{quartoId}")
    public Reserva inserirReserva(@PathVariable Long clienteId, @PathVariable Long quartoId, @Validated @RequestBody Reserva reserva) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + clienteId));

        Quarto quarto = quartoRepository.findById(quartoId)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto não encontrado com o ID: " + quartoId));

        reserva.setCliente(cliente);
        reserva.setQuarto(quarto);

        return reservaRepository.save(reserva);
    }

    // Alterar reserva existente
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> alterarReserva(@PathVariable Long id, @Validated @RequestBody Reserva reservaAtualizada) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada com o ID: " + id));

        reserva.setDataInicio(reservaAtualizada.getDataInicio());
        reserva.setDataFim(reservaAtualizada.getDataFim());
        reserva.setQuantidadeDias(reservaAtualizada.getQuantidadeDias());
        reserva.setValorReserva(reservaAtualizada.getValorReserva());

        final Reserva reservaAtualizadaFinal = reservaRepository.save(reserva);
        return ResponseEntity.ok(reservaAtualizadaFinal);
    }

    // Excluir reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> excluirReserva(@PathVariable Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada com o ID: " + id));

        reservaRepository.delete(reserva);

        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Reserva excluída com sucesso", Boolean.TRUE);
        return ResponseEntity.ok(resposta);
    }
}
