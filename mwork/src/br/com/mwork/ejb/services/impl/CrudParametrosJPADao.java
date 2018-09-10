package br.com.mwork.ejb.services.impl;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.mwork.entities.Parametro;
/**
 * @author Adriano
 *
 */
@Stateless
public class CrudParametrosJPADao {
	
	private static CrudParametrosJPADao instance;
	
	@Inject
	private EntityManager entityManager;

	public static CrudParametrosJPADao getInstance() {
		if (instance == null) {
			instance = new CrudParametrosJPADao();
		}
		return instance;
	}

	private CrudParametrosJPADao() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mwork");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}

	public Parametro getById(final int id) {
		return entityManager.find(Parametro.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Parametro> findAll() {
		return (ArrayList<Parametro>) entityManager.createQuery("FROM " + Parametro.class.getName()).getResultList();
	}

	public void persist(Parametro parametro) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(parametro);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Parametro parametro) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(parametro);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Parametro parametro) {
		try {
			entityManager.getTransaction().begin();
			parametro = entityManager.find(Parametro.class, parametro.getParametroId()); 
			entityManager.remove(parametro);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Parametro parametro = getById(id);
			remove(parametro);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}