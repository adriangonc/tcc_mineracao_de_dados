package br.com.mwork.ejb.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.mwork.ejb.services.MineracaoService;
import br.com.mwork.entities.TbDadosMinerado;

@Stateless
public class MineracaoDadosServiceJPADao implements MineracaoService {

	@Inject
	private EntityManager em;

	@Override
	public List<TbDadosMinerado> buscarDadosMinerados() throws Exception {
		return em.createQuery("FROM " + TbDadosMinerado.class.getName()).getResultList();
	}

	@Override
	public void inserirDadosMinerados(TbDadosMinerado dados) throws Exception {
		em.persist(dados);
	}

}