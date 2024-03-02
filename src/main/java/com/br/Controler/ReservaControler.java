package com.br.Controler;

import com.br.Repository.ReservaRepository;
import com.br.modelos.Reserva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/c_reservas/")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservaControler {

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReserva(@PathVariable Long id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        return reservaOptional.map(reserva -> new ResponseEntity<>(reserva, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/reserva")
    public ResponseEntity<Reserva> criarReserva(@RequestBody Reserva reserva) {
        Reserva novaReserva = reservaRepository.save(reserva);
        return new ResponseEntity<>(novaReserva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reservaAtualizada) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        if (reservaOptional.isPresent()) {
            Reserva reservaExistente = reservaOptional.get();
            reservaExistente.setCliente(reservaAtualizada.getCliente());
            reservaExistente.setQuarto(reservaAtualizada.getQuarto());
            reservaExistente.setDataInicio(reservaAtualizada.getDataInicio());
            reservaExistente.setDataFim(reservaAtualizada.getDataFim());
            reservaExistente.setQuantidadeDias(reservaAtualizada.getQuantidadeDias());
            reservaExistente.setValorReserva(reservaAtualizada.getValorReserva());

            Reserva reservaAtualizadaSalva = reservaRepository.save(reservaExistente);
            return new ResponseEntity<>(reservaAtualizadaSalva, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletarReserva(@PathVariable Long id) {
        try {
            reservaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
