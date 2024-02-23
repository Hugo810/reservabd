package com.br.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reserva")
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
	@Column(name="datainicio")
    private Date dataInicio;
	@Column(name="datafim")
    private Date dataFim;
	@Column(name="quantidadeDias")
    private int quantidadeDias;
	@Column(name="valorReserva")
    private double valorReserva;
	
	

    public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Construtor
    public Reserva(Long id, Cliente cliente, Quarto quarto, Date dataInicio, Date dataFim, int quantidadeDias, double valorReserva) {
        this.id = id;
        this.cliente = cliente;
        this.quarto = quarto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.quantidadeDias = quantidadeDias;
        this.valorReserva = valorReserva;
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

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
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

	// toString para representação textual do objeto
    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", quarto=" + quarto +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", quantidadeDias=" + quantidadeDias +
                ", valorReserva=" + valorReserva +
                '}';
    }
}
