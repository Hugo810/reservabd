package com.br.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="quarto")
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="tipo")
    @Enumerated(EnumType.STRING)
    private TipoQuarto tipo;

    @Column(name="numero")
    private int numero;
    
    @Column(name="preco")
    private Double preco;
    
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private StatusQuarto status;
    
    //@Column(name="disponivel")
    //private boolean disponivel;

    public Quarto() {
        super();
    }

    public Quarto(Long id, TipoQuarto tipo, int numero, Double preco, StatusQuarto status, boolean disponivel) {
        super();
        this.id = id;
        this.tipo = tipo;
        this.numero = numero;
        this.preco = preco;
        this.status = status;
        //this.disponivel = disponivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public void setTipo(TipoQuarto tipo) {
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public StatusQuarto getStatus() {
        return status;
    }

    public void setStatus(StatusQuarto status) {
        this.status = status;
    }

    //public boolean isDisponivel() {
      //  return disponivel;
    //}

    //public void setDisponivel(boolean disponivel) {
      //  this.disponivel = disponivel;
    //}
}
