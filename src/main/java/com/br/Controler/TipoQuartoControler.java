package com.br.Controler;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.br.modelos.TipoQuarto;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tipos-quarto")
public class TipoQuartoControler {

    @GetMapping
    public TipoQuarto[] getTiposQuarto() {
        return TipoQuarto.values();
    }
}


