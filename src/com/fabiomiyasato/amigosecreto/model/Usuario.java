package com.fabiomiyasato.amigosecreto.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "USUARIO", catalog = "amigosecretodb", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "SENHA", nullable = false)
	private String senha;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	private Set<SorteioUsuario> sorteioUsuario = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<SorteioUsuario> getSorteioUsuario() {
		return sorteioUsuario;
	}

	public void setSorteioUsuario(Set<SorteioUsuario> sorteioUsuario) {
		this.sorteioUsuario = sorteioUsuario;
	}

}
