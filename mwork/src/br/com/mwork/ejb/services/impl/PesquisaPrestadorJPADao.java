package br.com.mwork.ejb.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.mwork.ejb.services.PesquisaPrestadorService;
import br.com.mwork.entities.TbDadosMinerado;
import br.com.mwork.entities.TbInformacoesUsuario;
import br.com.mwork.entities.ViewPrestador;

@Stateless
public class PesquisaPrestadorJPADao implements PesquisaPrestadorService {

	@Inject
	private EntityManager em;

	@Override
	public List<ViewPrestador> buscarPrestador(TbInformacoesUsuario prestador) throws Exception {
		return em.createQuery("FROM " + ViewPrestador.class.getName()).getResultList();
	}

	@Override
	public void avaliarPrestador(TbInformacoesUsuario prestador) throws Exception {
		em.merge(prestador);

	}

	@Override
	public List<TbDadosMinerado> buscarPrestadorMinerado(String atividade) throws Exception {
		/*Query query = em.createNativeQuery("select * from tb_dados_minerados where titulo like '%" + atividade + "%'");
		return (List<TbDadosMinerado>) query.getResultList();*/
		return em.createQuery("FROM " + TbDadosMinerado.class.getName()).getResultList();
	}

}