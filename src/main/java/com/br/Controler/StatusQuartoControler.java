package com.br.Controler;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.modelos.StatusQuarto; // Importe o enum correto

@RestController
@RequestMapping("/api/status-quarto")
@CrossOrigin(origins = "http://localhost:4200")
public class StatusQuartoControler {
	    
    @GetMapping
    public StatusQuarto[] getStatusQuarto() {
        return StatusQuarto.values(); // Retorne os valores do enum StatusQuarto
    }
}
