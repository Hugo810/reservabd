package com.br.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.modelos.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	List<Usuario> findAll();
}
