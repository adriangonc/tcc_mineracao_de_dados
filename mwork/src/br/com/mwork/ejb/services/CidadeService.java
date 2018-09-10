package br.com.mwork.ejb.services;

import java.util.List;

import br.com.mwork.entities.ViewCidade;;

/**
 * @author Adriano
 *
 */
public interface CidadeService {
	public List<ViewCidade> buscarCidade() throws Exception;

	public ViewCidade getById(final int id) throws Exception;

}