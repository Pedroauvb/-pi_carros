package br.com.carros.carros.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VendaCarro {
	@Id
	@GeneratedValue
	private Long id;
	private String nomeCarro;
	private BigDecimal valor;
	private Long usuarioId;
	private Long usuarioVendaId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCarro() {
		return nomeCarro;
	}
	public void setNomeCarro(String nomeCarro) {
		this.nomeCarro = nomeCarro;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Long getUsuarioVendaId() {
		return usuarioVendaId;
	}
	public void setUsuarioVendaId(Long usuarioVendaId) {
		this.usuarioVendaId = usuarioVendaId;
	}
	
}
