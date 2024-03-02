package com.br.Controler;

import com.br.Exception.ResourceNotFoundException;
import com.br.Repository.QuartoRepository;
import com.br.modelos.Quarto;
import com.br.modelos.StatusQuarto;
import com.br.modelos.TipoQuarto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/c_quarto/")
public class QuartoControler {

    @Autowired
    private QuartoRepository quartoRepository;

    @GetMapping("/quarto")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Quarto> listarQuartos() {
        return quartoRepository.findAll();
    }

    @GetMapping("/quarto/{id}")
    public ResponseEntity<Quarto> consultarQuarto(@PathVariable Long id) {
        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto não encontrado com o ID: " + id));
        return ResponseEntity.ok(quarto);
    }
    
    @GetMapping("/quarto/tipo/{tipo}")
    public List<Quarto> consultarPorTipo(@PathVariable TipoQuarto tipo) {
        List<Quarto> quartoPorTipo = this.quartoRepository.findByTipo(tipo);
        if (quartoPorTipo.isEmpty()) {
            throw new ResourceNotFoundException("Quartos não encontrados para o tipo: " + tipo);
        }
        return quartoPorTipo;
    }

    @GetMapping("/quarto/status/{status}")
    public List<Quarto> consultarPorStatus(@PathVariable StatusQuarto status) {
        List<Quarto> quartosPorStatus = quartoRepository.findByStatusQuarto(status);
        
        if (quartosPorStatus.isEmpty()) {
            throw new ResourceNotFoundException("Quartos não encontrados para o status: " + status);
        }
        
        return quartosPorStatus;
    }
 
    @PostMapping("/quarto")
    public Quarto inserirQuarto(@RequestBody Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    @PutMapping("/quarto/{id}")
    public ResponseEntity<Quarto> alterarQuarto(@PathVariable Long id, @RequestBody Quarto quartoAtualizado) {
        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto não encontrado com o ID: " + id));

        quarto.setTipo(quartoAtualizado.getTipo());
        quarto.setNumero(quartoAtualizado.getNumero());
        quarto.setStatus(quartoAtualizado.getStatus());

        final Quarto quartoAtualizadoFinal = quartoRepository.save(quarto);
        return ResponseEntity.ok(quartoAtualizadoFinal);
    }

    @DeleteMapping("/quarto/{id}")
    public ResponseEntity<Map<String, Boolean>> excluirQuarto(@PathVariable Long id) {
        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto não encontrado com o ID: " + id));

        quartoRepository.delete(quarto);

        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Quarto excluído com sucesso", Boolean.TRUE);
        return ResponseEntity.ok(resposta);
    }   
}
