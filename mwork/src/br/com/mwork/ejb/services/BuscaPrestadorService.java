package br.com.mwork.ejb.services;

import java.util.List;

import br.com.mwork.entities.ViewPrestador;;

/**
 * @author Adriano
 *
 */
public interface BuscaPrestadorService {
	public List<ViewPrestador> buscarPrestador(int estadoSelecionado, String cidadeSelecionada) throws Exception;

	public ViewPrestador getById(final int id) throws Exception;

}