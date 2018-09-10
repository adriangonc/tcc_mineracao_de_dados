package br.com.mwork.ejb.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.mwork.ejb.services.AtividadeService;
import br.com.mwork.entities.TbAtividade;

@Stateless
public class AtividadeServiceJPADao implements AtividadeService {

	@Inject
	private EntityManager em;

	@Override
	public List<TbAtividade> buscarAtividade() {
		return em.createQuery("FROM " + TbAtividade.class.getName()).getResultList();
	}

	@Override
	public void inserirAtividade(TbAtividade atividade) {
		em.persist(atividade);

	}

	@Override
	public void removerAtividade(final int id) {
		try {
			TbAtividade atividade = getById(id);
			em.remove(atividade);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// @Override
	public TbAtividade getById(final int id) {
		return em.find(TbAtividade.class, id);
	}

	@Override
	public void alterarAtividade(TbAtividade atividade) {
		em.merge(atividade);

	}

}