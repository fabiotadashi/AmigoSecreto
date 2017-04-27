package com.fabiomiyasato.amigosecreto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fabiomiyasato.amigosecreto.model.Sorteio;
import com.fabiomiyasato.amigosecreto.model.SorteioUsuario;

public class SorteioUsuarioDAO {

	protected EntityManager em;

	public void adicionar(SorteioUsuario sorteioUsuario) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(sorteioUsuario);
		em.getTransaction().commit();
		em.close();
	}

	public List<SorteioUsuario> buscarPorUsuario(String email) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select su from SorteioUsuario su where usuario.email = :email");
		query.setParameter("email", email);
		return query.getResultList();
	}

	
	public void atualizar(SorteioUsuario sorteioUsuario) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(sorteioUsuario);
		em.getTransaction().commit();
		em.close();
	}

}
