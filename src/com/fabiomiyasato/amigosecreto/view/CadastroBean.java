package com.fabiomiyasato.amigosecreto.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.fabiomiyasato.amigosecreto.dao.UsuarioDAO;
import com.fabiomiyasato.amigosecreto.model.Usuario;

@ManagedBean
@SessionScoped
public class CadastroBean implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private Usuario usuario;

	@PostConstruct
    public void init(){
		setUsuario(new Usuario());
    }
	
	public String cadastrar() {
		UsuarioDAO dao = new UsuarioDAO();
		dao.adicionar(getUsuario());
		FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "Amigo Secreto",  "Usuario cadastrado com sucesso") );
			return "login";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}