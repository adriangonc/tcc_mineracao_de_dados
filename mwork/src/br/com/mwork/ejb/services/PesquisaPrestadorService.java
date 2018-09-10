package br.com.mwork.ejb.services;

import java.util.List;

import br.com.mwork.entities.TbDadosMinerado;
import br.com.mwork.entities.TbInformacoesUsuario;
import br.com.mwork.entities.ViewPrestador;

public interface PesquisaPrestadorService {
	
	public void avaliarPrestador(TbInformacoesUsuario prestador) throws Exception;

	public List<ViewPrestador> buscarPrestador(TbInformacoesUsuario prestador) throws Exception;
	
	public List<TbDadosMinerado> buscarPrestadorMinerado(String atividade) throws Exception;
	
}
