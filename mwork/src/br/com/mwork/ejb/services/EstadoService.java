package br.com.mwork.ejb.services;

import java.util.List;

import br.com.mwork.entities.TbEstado;;

/**
 * @author Adriano
 *
 */
public interface EstadoService {
	public List<TbEstado> buscarEstado() throws Exception;

	public TbEstado getById(final int id) throws Exception;

}