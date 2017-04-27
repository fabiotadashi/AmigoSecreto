package com.fabiomiyasato.amigosecreto.view;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.fabiomiyasato.amigosecreto.dao.SorteioDAO;
import com.fabiomiyasato.amigosecreto.dao.SorteioUsuarioDAO;
import com.fabiomiyasato.amigosecreto.dao.UsuarioDAO;
import com.fabiomiyasato.amigosecreto.model.Sorteio;
import com.fabiomiyasato.amigosecreto.model.SorteioUsuario;
import com.fabiomiyasato.amigosecreto.model.Usuario;
import com.fabiomiyasato.amigosecreto.util.SessionUtils;

@ManagedBean
@SessionScoped
public class AmigoSecretoBean implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	private List<SorteioUsuario> sorteioUsuario;

	@PostConstruct
	public void init() {
		atualizarList();
	}

	private void atualizarList() {
		SorteioUsuarioDAO dao = new SorteioUsuarioDAO();
		sorteioUsuario = dao.buscarPorUsuario(SessionUtils.getEmail());
	}

	public List<SorteioUsuario> getSorteioUsuario() {
		return sorteioUsuario;
	}

	public void setSorteioUsuario(List<SorteioUsuario> sorteioUsuario) {
		this.sorteioUsuario = sorteioUsuario;
	}


}