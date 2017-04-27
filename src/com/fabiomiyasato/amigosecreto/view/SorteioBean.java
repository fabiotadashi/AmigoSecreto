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
import com.fabiomiyasato.amigosecreto.util.SorteioUtil;

@ManagedBean
@SessionScoped
public class SorteioBean implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	private List<Sorteio> sorteios;

	private String titulo;

	@PostConstruct
	public void init() {
		atualizarList();
	}

	public String criar() {
		UsuarioDAO usuarioDao = new UsuarioDAO();
		SorteioDAO dao = new SorteioDAO();
		Sorteio sorteio = new Sorteio();
		sorteio.setTitulo(getTitulo());
		sorteio.setData(new Date(Calendar.getInstance().getTimeInMillis()));
		Usuario usuario = usuarioDao.getByEmail(SessionUtils.getEmail());
		sorteio.setCriador(usuario);
		sorteio.setSorteioRealizado(false);
		dao.adicionar(sorteio);
		adicionarUsuarioAoSorteio(sorteio, usuario);
		setTitulo("");
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("message",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Amigo Secreto", "Sorteio cadastrado com sucesso"));
		atualizarList();
		return "home.xhtml";
	}

	private void adicionarUsuarioAoSorteio(Sorteio sorteio, Usuario usuario) {
		SorteioUsuarioDAO sorteioUsuarioDao = new SorteioUsuarioDAO();
		SorteioUsuario sorteioUsuario = new SorteioUsuario();
		sorteioUsuario.setSorteio(sorteio);
		sorteioUsuario.setUsuario(usuario);
		sorteio.getSorteioUsuario().add(sorteioUsuario);
		sorteioUsuarioDao.adicionar(sorteioUsuario);
	}

	public void participar(Sorteio sorteio) {
		UsuarioDAO usuarioDao = new UsuarioDAO();
		Usuario usuario = usuarioDao.getByEmail(SessionUtils.getEmail());
		adicionarUsuarioAoSorteio(sorteio, usuario);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "Amigo Secreto",
				"Você está participando deste amigo secreto"));
	}

	public void sortear(int sorteioId) {
		SorteioDAO dao = new SorteioDAO();
		Sorteio sorteio = dao.buscarPorId(sorteioId);
		SorteioUtil.sortear(sorteio);
		sorteio.setSorteioRealizado(true);
		dao.atualizar(sorteio);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("message",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Amigo Secreto", "Amigo secreto sorteado"));
	}

	private void atualizarList() {
		SorteioDAO dao = new SorteioDAO();
		sorteios = dao.buscarNaoRealizado();
	}

	public List<Sorteio> getMeusSorteios() {
		String emailUsuario = SessionUtils.getEmail();
		return sorteios.stream().filter(s -> s.getCriador().getEmail().equals(emailUsuario))
				.collect(Collectors.toList());
	}

	public String getEmailUsuario() {
		return SessionUtils.getEmail();
	}

	public List<Sorteio> getSorteios() {
		return sorteios;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}