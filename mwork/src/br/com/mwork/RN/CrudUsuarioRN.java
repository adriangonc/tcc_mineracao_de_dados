package br.com.mwork.RN;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.mwork.ejb.services.UsuarioService;
import br.com.mwork.entities.Parametro;
import br.com.mwork.entities.User;
import br.com.mwork.util.EnviarEmailUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Adriano
 * @since 02/05/2016
 */
@RequestScoped
@Named
public class CrudUsuarioRN implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8867740943666574735L;

	@Getter
	@Setter
	private List<User> listaUsuario;

	@Getter
	@Setter
	private EnviarEmailUtil enviarEmail;
	
	@Getter
	@Setter
	private List<Parametro> listaParametroSistema;
	
	@Inject
	CrudParametroNegocioRN crudParametroRN;
	
	@Getter
	@Setter
	private String smtp;

	@Getter
	@Setter
	private int smtpPort;

	@Getter
	@Setter
	private boolean sslOn;

	@Getter
	@Setter
	private String emailRemetente;

	@Getter
	@Setter
	private String senhaRemetente;

	@Getter
	@Setter
	private String altenticacaoEmail;

	@EJB
	private UsuarioService serviceUsuario;
	
	@Getter
	@Setter
	private User usuario;

	@PostConstruct
	public void iniciarInterface() {
		listaParametroSistema = new ArrayList<Parametro>();
		listaUsuario = new ArrayList<User>();
		enviarEmail = new EnviarEmailUtil();
		usuario = new User();
		controleInicial();
	}

	private void controleInicial() {
	}

	public List<User> buscarUsuario() {

		try {
			listaUsuario = serviceUsuario.buscarUsuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaUsuario;
	}

	public void removerUsuario(User usuario) {
		try {
			serviceUsuario.removerUsuario(usuario.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Calendar dataHoraAtual() {
		Calendar data = Calendar.getInstance();
		return data;
	}

	public void limparTela() {
		iniciarInterface();
	}
	
	public void buscarParametroEmail() {
		try {
			listaParametroSistema = crudParametroRN.buscarParametro();

			for (Parametro parametro : listaParametroSistema) {
				if (parametro.getParametroId().equals(111)) {
					setEmailRemetente(parametro.getParametroValor());
				}
				if (parametro.getParametroId().equals(116)) {
					setSenhaRemetente(parametro.getParametroValor());
				}
				if (parametro.getParametroId().equals(115)) {
					setAltenticacaoEmail(parametro.getParametroValor());
				}
				if (parametro.getParametroId().equals(113)) {
					setSmtp(parametro.getParametroValor());
				}
				if (parametro.getParametroId().equals(112)) {
					setSmtpPort(Integer.parseInt(parametro.getParametroValor()));
				}
				if (parametro.getParametroId().equals(114)) {
					if (parametro.getParametroValor().equals("true")) {
						setSslOn(true);
					} else {
						setSslOn(false);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserirUsuario(User usuario) {
		buscarParametroEmail();
		try {
			usuario.setPassword(enviarEmail.enviaEmailNovoUsuario(usuario.getEmail(), smtp, smtpPort, sslOn, emailRemetente, senhaRemetente,
					altenticacaoEmail));
			usuario.setPassword(
					gerarSenhaAleatoria() + criptografarSenha(usuario.getPassword()) + gerarSenhaAleatoria());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			serviceUsuario.inserirUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterarUsuario(User usuarioSelecionado) {

		try {
			serviceUsuario.alterarUsuario(usuarioSelecionado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterarSenhaUsuario(User usuarioSelecionado, String senhaAtual) {

		try {
			if (verificarSenhaAntiga(senhaAtual, usuarioSelecionado)) {
				usuarioSelecionado.setPassword(gerarSenhaAleatoria()
						+ criptografarSenha(usuarioSelecionado.getPassword()) + gerarSenhaAleatoria());
				serviceUsuario.alterarSenhaUsuario(usuarioSelecionado);
				addMessage("Registro modificado com sucesso.");
			} else {
				addMessageErro("A senha atual está incorreta.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void recuperarSenhaUsuario(User usuarioSelecionado, String smtp, Integer smtpPort, Boolean sslOn,
			String emailRemetente, String senhaRemetente, String altenticacaoEmail) {
		String senhaAleatoria = "";
		try {
			if (verificarEmail(usuarioSelecionado)) {
				senhaAleatoria = enviarEmail.enviaRecuperacaoEmail(usuarioSelecionado.getEmail(), smtp, smtpPort, sslOn,
						emailRemetente, senhaRemetente, altenticacaoEmail);
				usuarioSelecionado
						.setPassword(gerarSenhaAleatoria() + criptografarSenha(senhaAleatoria) + gerarSenhaAleatoria());
				serviceUsuario.alterarSenhaUsuario(usuarioSelecionado);
				addMessage("Senha enviada para seu email.");
			} else {
				addMessageErro("Email não cadastrado.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean verificarEmail(User usuarioSelecionado) {
		try {
			if (serviceUsuario.buscarSenhaUsuario(usuarioSelecionado) != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private Boolean verificarSenhaAntiga(String senhaAtual, User usuario) {
		try {
			if (criptografarSenha(senhaAtual).equals(serviceUsuario.buscarSenhaUsuario(usuario).substring(5, 69))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Adriano
	 * @since 02/05/2016 Criptografa a senha antes de persistir no banco usando
	 *        o hash de algoritmo SHA-264 em exadecimal.
	 * 
	 */
	public String criptografarSenha(String senha) throws UnsupportedEncodingException {
		String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(senha);
		return sha256hex;
	}

	private String gerarSenhaAleatoria() {
		String[] carct = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

		String senha = "";

		for (int x = 0; x < 5;) {
			int j = (int) (Math.random() * carct.length);
			senha += carct[j];
			x++;
		}
		return senha;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessageErro(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
