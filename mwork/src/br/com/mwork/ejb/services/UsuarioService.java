package br.com.mwork.ejb.services;

import java.util.List;

import br.com.mwork.entities.TbInformacoesUsuario;
import br.com.mwork.entities.User;

/**
 * @author Adriano
 * @since 08/08/2016
 * 
 */
public interface UsuarioService {
	public List<User> buscarUsuario() throws Exception;

	public void inserirUsuario(User usuario) throws Exception;

	public void removerUsuario(int usuario) throws Exception;

	public void alterarUsuario(User usuario) throws Exception;

	public void alterarSenhaUsuario(User usuario) throws Exception;

	public String buscarSenhaUsuario(User usuario) throws Exception;

	public void inserirUsuarioDoSistema(User usuario, TbInformacoesUsuario usuarioDoSistema) throws Exception;

	public TbInformacoesUsuario buscarUsuarioSistemaPorId(int id) throws Exception;

	public User buscarUsuarioPorId(String username) throws Exception;

	public void atualizarUsuario(User usuario, TbInformacoesUsuario informacoesUsuario) throws Exception;

}
