package br.com.mwork.ejb.services;

import java.util.List;

import br.com.mwork.entities.Parametro;

/**
 * @author Adriano
 *
 */
public interface ParametroService {
	public List<Parametro> buscarParametro() throws Exception;
	
	public void inserirParametro(Parametro parametro) throws Exception;
	
	public void removerParametro(int parametro) throws Exception;
	
	public void alterarParametro(Parametro parametro) throws Exception;
	
	public Parametro getById(final int id)throws Exception;

	public void atualizarParametroPorID(int sequencialListaDebito) throws Exception;
	

}
