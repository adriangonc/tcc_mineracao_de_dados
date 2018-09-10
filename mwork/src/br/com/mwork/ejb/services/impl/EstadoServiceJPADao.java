package br.com.mwork.ejb.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.mwork.ejb.services.EstadoService;
import br.com.mwork.entities.TbEstado;

@Stateless
public class EstadoServiceJPADao implements EstadoService {

	@Inject
	private EntityManager em;

	@Override
	public List<TbEstado> buscarEstado() {
		return em.createQuery("FROM " + TbEstado.class.getName()).getResultList();
	}

	public TbEstado getById(final int id) {
		return em.find(TbEstado.class, id);
	}

}