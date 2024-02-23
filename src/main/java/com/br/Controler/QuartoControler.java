package com.br.Controler;

import com.br.Exception.ResourceNotFoundException;
import com.br.Repository.QuartoRepository;
import com.br.modelos.Quarto;
import com.br.modelos.StatusQuarto;

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

    // Listar todos os quartos
    @GetMapping("/quarto")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Quarto> listarQuartos() {
        return quartoRepository.findAll();
    }

    // Consultar quarto por ID
    @GetMapping("/quarto/{id}")
    public ResponseEntity<Quarto> consultarQuarto(@PathVariable Long id) {
        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto não encontrado com o ID: " + id));
        return ResponseEntity.ok(quarto);
    }
 // Método para consultar quartos por status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Quarto>> consultarPorStatus(@PathVariable StatusQuarto status) {
        // Busca os quartos por status no repositório
        List<Quarto> quartosPorStatus = quartoRepository.findByStatusQuato(status);
        
        // Verifica se existem quartos com o status especificado
        if (quartosPorStatus.isEmpty()) {
            throw new ResourceNotFoundException("Não foram encontrados quartos com o status: " + status);
        }
        
        // Retorna a lista de quartos encontrados
        return ResponseEntity.ok(quartosPorStatus);
    }
    

    // Inserir novo quarto
    @PostMapping("/quarto")
    public Quarto inserirQuarto(@RequestBody Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    // Alterar quarto existente
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

    // Excluir quarto
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
