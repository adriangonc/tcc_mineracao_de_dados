package br.com.mwork.util;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HibernateUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@PersistenceContext
	private static EntityManager em;
	
	
	public static EntityManager getEntityManager() {
		return em;
	}

}
