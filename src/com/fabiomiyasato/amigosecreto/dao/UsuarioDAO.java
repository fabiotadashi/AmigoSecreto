package com.fabiomiyasato.amigosecreto.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fabiomiyasato.amigosecreto.model.Usuario;

public class UsuarioDAO {

	protected EntityManager em;
	
	public Usuario getByEmail(String email) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Usuario u where email = :email");
		query.setParameter("email", email);
		return (Usuario) query.getSingleResult();
	}
	
	public void adicionar(Usuario usuario) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
	}

}
