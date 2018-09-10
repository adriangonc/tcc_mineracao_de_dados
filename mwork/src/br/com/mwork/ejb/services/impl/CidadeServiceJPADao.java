package br.com.mwork.ejb.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.mwork.ejb.services.CidadeService;
import br.com.mwork.entities.ViewCidade;

@Stateless
public class CidadeServiceJPADao implements CidadeService {

	@Inject
	private EntityManager em;

	@Override
	public List<ViewCidade> buscarCidade() {
		return em.createQuery("FROM " + ViewCidade.class.getName()).getResultList();
	}

	public ViewCidade getById(final int id) {
		return em.find(ViewCidade.class, id);
	}

}