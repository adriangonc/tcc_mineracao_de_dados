package br.com.mwork.ejb.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.mwork.ejb.services.UsuarioService;
import br.com.mwork.entities.TbInformacoesUsuario;
import br.com.mwork.entities.User;

@Stateless
public class CrudUsuarioJPADao implements UsuarioService {

	@Inject
	private EntityManager em;

	@Override
	public List<User> buscarUsuario() {
		return em.createQuery("FROM " + User.class.getName()).getResultList();
	}

	@Override
	public void inserirUsuario(User usuario) {
		em.persist(usuario);
	}

	@Override
	public void removerUsuario(final int id) {
		try {
			User usuario = getById(id);
			em.remove(usuario);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public User getById(final int id) {
		return em.find(User.class, id);
	}

	@Override
	public void alterarUsuario(User usuario) {
		em.merge(usuario);
	}

	@Override
	public void alterarSenhaUsuario(User usuario) {
		try {
			User userModificado = new User();
			userModificado = getById(buscarUsuarioPorEmail(usuario));
			userModificado.setPassword(usuario.getPassword());
			em.merge(userModificado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer buscarUsuarioPorEmail(User usuario) throws Exception {
		Query query = em.createNativeQuery("select id from users where email = ?");
		query.setParameter(1, usuario.getEmail());
		return (Integer) query.getSingleResult();
	}

	@Override
	public String buscarSenhaUsuario(User usuario) throws Exception {
		Query query = em.createNativeQuery("select password from users where email = ?");
		query.setParameter(1, usuario.getEmail());
		return (String) query.getSingleResult();
	}

	@Override
	public void inserirUsuarioDoSistema(User Usuario, TbInformacoesUsuario usuarioDoSistema) throws Exception {
		try {
			em.persist(Usuario);
			Query query = em.createNativeQuery("select max(id) from users");
			int idUsuario = (int) query.getSingleResult();
			usuarioDoSistema.setId(idUsuario);
			em.persist(usuarioDoSistema);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public TbInformacoesUsuario buscarUsuarioSistemaPorId(int id) throws Exception {
		return em.find(TbInformacoesUsuario.class, id);
	}

	@Override
	public User buscarUsuarioPorId(String username) throws Exception {
		return em.find(User.class, buscarUsuarioUsename(username));
	}

	public Integer buscarUsuarioUsename(String usuario) throws Exception {
		Query query = em.createNativeQuery("select id from users where email = ?");
		query.setParameter(1, usuario);
		return (Integer) query.getSingleResult();
	}

	@Override
	public void atualizarUsuario(User usuario, TbInformacoesUsuario informacoesUsuario) throws Exception {
		em.merge(usuario);
		em.merge(informacoesUsuario);
	}

}