package com.fabiomiyasato.amigosecreto.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LoginDAO {

	protected EntityManager em;
	
	public boolean validar(String email, String senha) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Usuario u where email = :email and senha = :senha");
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		return !query.getResultList().isEmpty();
	}

}
