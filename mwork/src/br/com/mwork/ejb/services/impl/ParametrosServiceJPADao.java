package br.com.mwork.ejb.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.mwork.ejb.services.ParametroService;
import br.com.mwork.entities.Parametro;

@Stateless
public class ParametrosServiceJPADao implements ParametroService {

	@Inject
	private EntityManager em;

	@Override
	public List<Parametro> buscarParametro() {
		return em.createQuery("FROM " + Parametro.class.getName()).getResultList();
	}

	@Override
	public void inserirParametro(Parametro parametro) {
		em.persist(parametro);
		
	}

	@Override
	public void removerParametro(final int id) {
			try {
				Parametro parametro = getById(id);
				em.remove(parametro);	
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		
	}
	
	@Override
	public Parametro getById(final int id) {
		return em.find(Parametro.class, id);
	}


	@Override
	public void alterarParametro(Parametro parametro) {
		em.merge(parametro);
		
	}
	
	@Override 
	public void atualizarParametroPorID(int idSequencialListaDebito){
		Query query = em.createNativeQuery("update parametro set parametro_valor = ? where parametro_id = " + idSequencialListaDebito);
		query.setParameter(1, Integer.parseInt(getUltimaSequencialListaDebito(idSequencialListaDebito)) + 1);

		query.executeUpdate();
	}
	
	public String getUltimaSequencialListaDebito(int idSequencialListaDebito) {
		Query query = em.createNativeQuery("select parametro_valor from parametro where parametro_id = " + idSequencialListaDebito);

		return (String) query.getSingleResult();
	}
	
}