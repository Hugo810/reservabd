package com.br.Controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.Exception.ResourceNotFoundException;
import com.br.Repository.ClienteRepository;
import com.br.modelos.Cliente;
import com.br.modelos.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore; // Importação adicionada

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/c_cliente/")
@RestController
public class ClienteControler {

    @Autowired
    private ClienteRepository mRep;

    // Listar todos os clientes
    @GetMapping("/cliente")
    public List<Cliente> listar() {
        return this.mRep.findAll();
    }

    // Consultar cliente por ID
    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> consultar(@PathVariable Long id) {
        Cliente cliente = this.mRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));
        return ResponseEntity.ok(cliente);
    }
    @GetMapping("/cliente/nome/{nome}")
    public ResponseEntity<List<Cliente>> consultarPorNome(@PathVariable String nome) {
        List<Cliente> clientes = this.mRep.findByNome(nome);
        if (clientes.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum cliente encontrado com o nome: " + nome);
        }
        return ResponseEntity.ok(clientes);
    }
    
    @GetMapping("/cliente/tipo/{tipo}")
    public List<Cliente> consultarPorTipo(@PathVariable TipoCliente tipo) {
        List<Cliente> clientesPorTipo = this.mRep.findByTipo(tipo);
        if (clientesPorTipo.isEmpty()) {
            throw new ResourceNotFoundException("Clientes não encontrados para o tipo: " + tipo);
        }
        return clientesPorTipo;
    }

    // Inserir novo cliente
    @PostMapping("/cliente")
    public Cliente inserir(@RequestBody Cliente cliente) {
        return this.mRep.save(cliente);
    }

    // Alterar cliente existente
    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> alterar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteLocalizado = this.mRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));

        clienteLocalizado.setNome(cliente.getNome());
        clienteLocalizado.setEmail(cliente.getEmail());
        clienteLocalizado.setTipo(cliente.getTipo());

        Cliente atualizado = this.mRep.save(clienteLocalizado);
        return ResponseEntity.ok(atualizado);
    }

    // Excluir cliente por ID
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {
        Cliente clienteLocalizado = this.mRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));

        this.mRep.delete(clienteLocalizado);

        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Cliente excluído!", Boolean.TRUE);
        return ResponseEntity.ok(resposta);
    }
    
    // A anotação @JsonIgnore foi adicionada para evitar referências cíclicas durante a serialização dos objetos
    @JsonIgnore
    public List<Cliente> getReservas() {
        return this.mRep.findAll();
    }
}
