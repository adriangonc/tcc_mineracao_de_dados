package br.com.mwork.ejb.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.mwork.ejb.services.BuscaPrestadorService;
import br.com.mwork.entities.ViewPrestador;

@Stateless
public class PrestadorServiceJPADao implements BuscaPrestadorService {

	@Inject
	private EntityManager em;

	@Override
	public List<ViewPrestador> buscarPrestador(int estadoSelecionado, String cidadeSelecionada) {
		// return em.createQuery("FROM " +
		// ViewPrestador.class.getName()).getResultList();
		Query query = em.createNativeQuery("select * from view_prestador where cod_estado = " + estadoSelecionado
				+ " AND cod_ibge_cidade = " + cidadeSelecionada);
		return (List<ViewPrestador>) query.getResultList();
	}

	public ViewPrestador getById(final int id) {
		return em.find(ViewPrestador.class, id);
	}

}