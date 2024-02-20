package com.br.modelos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;





@Entity
@Table(name="quarto")
public class Quarto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	 @Column(name="tipo")
	 private TipoQuarto tipo;
	

	@Column(name="numero")
    private int numero;
	@Column(name="status")
    private String status;
	
	@OneToMany(mappedBy = "quarto")
	private List<Reserva> reservas;
	
	

    public Quarto() {
		super();
		// TODO Auto-generated constructor stub
	}

	


	public Quarto(Long id, TipoQuarto tipo, int numero, String status, List<Reserva> reservas) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.numero = numero;
		this.status = status;
		this.reservas = reservas;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	// toString para representação textual do objeto
    @Override
    public String toString() {
        return "Quarto{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", numero=" + numero +
                ", status='" + status + '\'' +
                '}';
    }
}

