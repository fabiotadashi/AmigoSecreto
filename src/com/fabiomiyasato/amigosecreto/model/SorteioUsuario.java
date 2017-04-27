package com.fabiomiyasato.amigosecreto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SORTEIO_USUARIO")
public class SorteioUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "USUARIO_ID")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "SORTEIO_ID")
	private Sorteio sorteio;

	@ManyToOne
	@JoinColumn(name = "AMIGO_SECRETO_ID")
	private Usuario amigoSecreto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Sorteio getSorteio() {
		return sorteio;
	}

	public void setSorteio(Sorteio sorteio) {
		this.sorteio = sorteio;
	}

	public Usuario getAmigoSecreto() {
		return amigoSecreto;
	}

	public void setAmigoSecreto(Usuario amigoSecreto) {
		this.amigoSecreto = amigoSecreto;
	}

}
