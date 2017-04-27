package com.fabiomiyasato.amigosecreto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fabiomiyasato.amigosecreto.model.Sorteio;

public class SorteioDAO {

	protected EntityManager em;

	public List<Sorteio> buscarPorUsuario(String email) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select u.sorteios from Usuario u where email = :email");
		query.setParameter("email", email);
		return query.getResultList();
	}

	public List<Sorteio> buscarNaoRealizado() {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select s from Sorteio s where sorteioRealizado = false");
		return query.getResultList();
	}

	public void adicionar(Sorteio sorteio) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(sorteio);
		em.getTransaction().commit();
		em.close();
	}

	public Sorteio buscarPorId(int sorteioId) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select s from Sorteio s where id = :id");
		query.setParameter("id", sorteioId);
		return (Sorteio) query.getSingleResult();
	}

	public void atualizar(Sorteio sorteio) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(sorteio);
		em.getTransaction().commit();
		em.close();
	}

}
