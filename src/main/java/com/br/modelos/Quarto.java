package com.br.modelos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;





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
	
	@Column(name="preco")
	private Double preco;
	
	@Column(name="status")
    private StatusQuarto status;
	
	@OneToMany(mappedBy = "quarto")
	private List<Reserva> reservas;
	
	

    public Quarto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public Quarto(Long id, TipoQuarto tipo, int numero, Double preco, StatusQuarto status, List<Reserva> reservas) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.numero = numero;
		this.preco = preco;
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

