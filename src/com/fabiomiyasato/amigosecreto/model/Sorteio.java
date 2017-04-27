package com.fabiomiyasato.amigosecreto.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "SORTEIO", catalog = "amigosecretodb", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Sorteio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;

	@Column(name = "TITULO", nullable = false)
	private String titulo;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sorteio")
	private Set<SorteioUsuario> sorteioUsuario = new HashSet<>();
	
	@Column(name = "SORTEIO_REALIZADO", nullable = false)
	private Boolean sorteioRealizado;
	
	@Column(name = "DATA", nullable = false)
	private Date data;
	
	@ManyToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="USUARIO_CRIADOR_ID")
	private Usuario criador;

	public String getSorteioRealizadoString(){
		return sorteioRealizado? "sim" : "nao";
	}
	
	public boolean participando(String email){
		boolean participando = false;
		for (SorteioUsuario sorteioUsuario : sorteioUsuario) {
			if(sorteioUsuario.getUsuario().getEmail().equals(email)){
				return true;
			}
		}
		return participando;
	}
	
	
	public int getQuantidadeParticipantes(){
		return sorteioUsuario.size();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Boolean getSorteioRealizado() {
		return sorteioRealizado;
	}

	public void setSorteioRealizado(Boolean sorteioRealizado) {
		this.sorteioRealizado = sorteioRealizado;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public Set<SorteioUsuario> getSorteioUsuario() {
		return sorteioUsuario;
	}

	public void setSorteioUsuario(Set<SorteioUsuario> sorteioUsuario) {
		this.sorteioUsuario = sorteioUsuario;
	}

}
