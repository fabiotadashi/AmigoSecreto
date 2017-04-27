package com.fabiomiyasato.amigosecreto.util;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fabiomiyasato.amigosecreto.dao.SorteioUsuarioDAO;
import com.fabiomiyasato.amigosecreto.model.Sorteio;
import com.fabiomiyasato.amigosecreto.model.SorteioUsuario;
import com.fabiomiyasato.amigosecreto.model.Usuario;

public class SorteioUtil {

	public static Sorteio sortear(Sorteio sorteio) {
		SorteioUsuarioDAO dao = new SorteioUsuarioDAO();
		List<Usuario> participantes = sorteio.getSorteioUsuario().stream().map(su -> su.getUsuario())
				.collect(Collectors.toList());
		Collections.shuffle(participantes);
		for (SorteioUsuario sorteioUsuario : sorteio.getSorteioUsuario()) {
			sorteioUsuario.setAmigoSecreto(
					getParticipante(sorteioUsuario.getUsuario(), participantes, sorteio.getSorteioUsuario()));
			dao.atualizar(sorteioUsuario);
		}

		return sorteio;
	}

	private static Usuario getParticipante(Usuario usuario, List<Usuario> participantes,
			Set<SorteioUsuario> sorteioUsuario) {
		Usuario amigoSecreto = null;
		for (Usuario participante : participantes) {
			if (participante.getId() != usuario.getId() && usuarioSemRelacao(participante, usuario, sorteioUsuario)) {
				amigoSecreto = participante;
				break;
			}
		}
		participantes.remove(amigoSecreto);
		return amigoSecreto;
	}

	private static boolean usuarioSemRelacao(Usuario participante, Usuario usuario,
			Set<SorteioUsuario> sorteioUsuario) {
		for (SorteioUsuario su : sorteioUsuario) {
			if(su.getUsuario().getId() == participante.getId() && su.getAmigoSecreto() != null && su.getAmigoSecreto().getId() == usuario.getId()){
				return false;
			}
			if(su.getUsuario().getId() == usuario.getId() && su.getAmigoSecreto() != null && su.getAmigoSecreto().getId() == participante.getId()){
				return false;
			}
		}
		return true;
	}


}