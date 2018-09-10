package br.com.mwork.ejb.services;

import java.util.List;

import br.com.mwork.entities.TbAtividade;

public interface AtividadeService {

	public List<TbAtividade> buscarAtividade() throws Exception;
	
	public void inserirAtividade(TbAtividade atividade) throws Exception;
	
	public void removerAtividade(int atividade) throws Exception;
	
	public void alterarAtividade(TbAtividade atividade) throws Exception;
	
}
