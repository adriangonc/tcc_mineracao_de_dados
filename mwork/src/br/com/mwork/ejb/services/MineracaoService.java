package br.com.mwork.ejb.services;

import java.util.List;

import br.com.mwork.entities.TbDadosMinerado;

public interface MineracaoService {

	public List<TbDadosMinerado> buscarDadosMinerados() throws Exception;
	
	public void inserirDadosMinerados(TbDadosMinerado dados) throws Exception;
			
}
