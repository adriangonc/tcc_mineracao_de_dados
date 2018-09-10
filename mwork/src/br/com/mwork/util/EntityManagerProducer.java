package br.com.mwork.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *  
 * @author Guilherme Finotti Carvalho
 *
 */
@ApplicationScoped
public class EntityManagerProducer {

	@PersistenceContext
	private EntityManager entityManager;

	//@RequestScoped
	@Produces
	protected EntityManager createEntityManager() {
		return this.entityManager;
	}

	/**
	 * 
	 * @param entityManager
	 */
	/*protected void closeEntityManager(@Disposes EntityManager entityManager) {
		if (entityManager.isOpen()) {
			entityManager.close();
		}
	}*/
}
