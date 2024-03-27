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
import com.br.Repository.UsuarioRepository;
import com.br.modelos.Usuario;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuarios/")
@RestController
public class UsuarioControler {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar todos os usuários
    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Consultar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> consultarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));
        return ResponseEntity.ok(usuario);
    }

    // Inserir novo usuário
    @PostMapping("/inserir")
    public Usuario inserirUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Alterar usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());

        final Usuario usuarioAtual = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioAtual);
    }

    // Excluir usuário por ID
    @DeleteMapping("/{id}")
    public Map<String, Boolean> excluirUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));

        usuarioRepository.delete(usuario);
        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Usuário excluído", Boolean.TRUE);
        return resposta;
    }
}
