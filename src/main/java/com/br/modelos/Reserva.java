package com.br.modelos;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "quantidade_dias")
    private int quantidadeDias;

    @Column(name = "valor_reserva")
    private double valorReserva;

    public Reserva() {
    }

    public Reserva(Long id, Cliente cliente, Quarto quarto, Date dataInicio, /*Date dataFim,*/ int quantidadeDias, double valorReserva) {
        this.id = id;
        this.cliente = cliente;
        this.quarto = quarto;
        this.dataInicio = dataInicio;
        this.quantidadeDias = quantidadeDias;
        this.valorReserva = valorReserva;
    }
    
    /*public int calcularQuantidadeDias() {
        long diffEmMillis = Math.abs(dataFim.getTime() - dataInicio.getTime());
        long diffEmDias = diffEmMillis / (24 * 60 * 60 * 1000);
        return quantidadeDias = (int) diffEmDias;
    }*/
    
    public void calcularValorReserva() {
        valorReserva = quantidadeDias * quarto.getPreco();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(int quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    public double getValorReserva() {
        return valorReserva;
    }

    public void setValorReserva(double valorReserva) {
        this.valorReserva = valorReserva;
    }

	
}
