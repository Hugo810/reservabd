package com.br.Controler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.Repository.QuartoRepository;
import com.br.Repository.ReservaRepository;
import com.br.modelos.Reserva;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/c_reservas")
public class ReservaControler {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private QuartoRepository quartoRepository;

    // Listar todas as reservas
    @GetMapping("/reserva")
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    // Buscar reserva por ID
    @GetMapping("/reserva/{id}")
    public ResponseEntity<Reserva> buscarReserva(@PathVariable Long id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        return reservaOptional.map(reserva -> new ResponseEntity<>(reserva, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar reserva
    @PostMapping("/reserva")
    public ResponseEntity<Reserva> criarReserva(@RequestBody Reserva reserva) {
        try {
            reserva.setDataInicio(new Date()); // Define a data de início como a data atual
            Reserva novaReserva = reservaRepository.save(reserva);
            return new ResponseEntity<>(novaReserva, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Atualizar reserva
    @SuppressWarnings("rawtypes")
    @PutMapping("/reserva/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reservaAtualizada) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        if (reservaOptional.isPresent()) {
            Reserva reservaExistente = reservaOptional.get();
            // Atualize os campos da reserva existente com os dados da reserva atualizada
            // ...

            // Convertendo as strings de data para instâncias de Date
            reservaExistente.setDataInicio(reservaAtualizada.getDataInicio());
            //reservaExistente.setDataFim(reservaAtualizada.getDataFim());

            Reserva reservaAtualizadaSalva = reservaRepository.save(reservaExistente);
            return new ResponseEntity<>(reservaAtualizadaSalva, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Método para converter uma string para Date
    private Date convertStringToDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // O formato da data deve corresponder ao formato da string
            return sdf.parse(dateString);
        } catch (ParseException e) {
            // Trate qualquer exceção de parsing de data aqui
            e.printStackTrace();
            return null;
        }
    }

}
