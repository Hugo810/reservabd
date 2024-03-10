package com.br.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.modelos.Cliente;
import com.br.modelos.TipoCliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByTipo(TipoCliente tipo);

	List<Cliente> findByNomeStartingWith(String nome);
	List<Cliente> findByNomeContainingIgnoreCase(String nome);
	
	@Query("SELECT c FROM Cliente c WHERE LOWER(c.nome) = LOWER(:nome)")
    List<Cliente> findByNomeIgnoreCase(String nome);

	List<Cliente> findByNome(String nome);
	}


