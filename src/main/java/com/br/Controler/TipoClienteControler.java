package com.br.Controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.modelos.TipoCliente;

@RestController
@RequestMapping("/api/tipos-cliente")
public class TipoClienteControler {

    @GetMapping
    public TipoCliente[] getTiposCliente() {
        return TipoCliente.values();
    }
}

