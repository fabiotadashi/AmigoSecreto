package com.fabiomiyasato.amigosecreto.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.fabiomiyasato.amigosecreto.dao.LoginDAO;
import com.fabiomiyasato.amigosecreto.util.SessionUtils;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String senha;
	private String email;

	public String logar() {
		LoginDAO dao = new LoginDAO();
		if (dao.validar(email, senha)) {
			SessionUtils.getSession().setAttribute("email", email);
			return "home";
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "Amigo Secreto",  "Email e/ou senha incorreto(s)") );
			return "login";
		}
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}