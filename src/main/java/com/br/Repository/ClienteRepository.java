package com.br.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.modelos.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
