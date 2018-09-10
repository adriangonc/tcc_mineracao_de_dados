package br.com.mwork.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.mwork.RN.CrudParametroNegocioRN;
import br.com.mwork.RN.CrudUsuarioRN;
import br.com.mwork.ejb.services.UsuarioService;
import br.com.mwork.entities.Parametro;
import br.com.mwork.entities.User;
import br.com.mwork.util.EnviarEmailUtil;
import br.com.mwork.util.ValidadorUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Adriano
 *
 */
@ManagedBean
@ViewScoped
public class RecuperaSenhaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5630169019337677631L;

	@Inject
	CrudUsuarioRN crudUsuarioRN;

	@Inject
	ValidadorUtil validadorUtil;

	@Inject
	EnviarEmailUtil enviarEmail;

	@Inject
	CrudParametroNegocioRN crudParametroRN;

	@Getter
	@Setter
	private User usuario;

	@Getter
	@Setter
	private User usuarioSelecionado;

	@Getter
	@Setter
	private String emailUsuario;

	@Getter
	@Setter
	private String senhaUsuario;

	@Getter
	@Setter
	private List<User> listaUsuario;

	@Getter
	@Setter
	private List<Parametro> listaParametroSistema;

	@Getter
	@Setter
	private List<Parametro> listaParametroNegocio;

	@EJB
	private UsuarioService serviceUsuario;

	@Getter
	@Setter
	private Parametro parametroSelecionado;

	@Getter
	@Setter
	private Boolean gerarNovaSenha;

	@Getter
	@Setter
	private String username;

	@Getter
	@Setter
	private String senhaAtual;

	@Getter
	@Setter
	private String senhaNova;

	@Getter
	@Setter
	private String senhaNovaRepetida;

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

	@PostConstruct
	public void iniciarInterface() {
		listaUsuario = new ArrayList<User>();
		parametroSelecionado = new Parametro();
		enviarEmail = new EnviarEmailUtil();
		usuario = new User();
		usuarioSelecionado = new User();
		controleInicial();
	}

	private void controleInicial() {
		setGerarNovaSenha(false);
		usuarioLogado();
		setSenhaAtual("");
		setSenhaNova("");
		setSenhaNovaRepetida("");
	}

	public void recuperarSenha() {
		buscarParametro();
		crudUsuarioRN.recuperarSenhaUsuario(usuarioSelecionado, smtp, smtpPort, sslOn, emailRemetente, senhaRemetente,
				altenticacaoEmail);
		limpar();
	}

	public void alterarSenhaUsuario() {
		try {

			if (validadorUtil.verificaInsesaoEspaco(usuarioSelecionado.getPassword())) {
				usuarioSelecionado.setEmail(username);
				crudUsuarioRN.alterarSenhaUsuario(usuarioSelecionado, senhaAtual);
			} else {
				addMessageErro("Voc� n�o pode inserir espa�o no in�cio do texto.");
			}
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(e.getMessage());
		}
	}

	public void inserirUsuario() {
		try {
			if ((validadorUtil.verificaInsesaoEspaco(usuario.getEmail())
					&& validadorUtil.verificaInsesaoEspaco(usuario.getName()))) {
				crudUsuarioRN.inserirUsuario(usuario);
				addMessage("Registro salvo com sucesso.");
			} else {
				addMessageErro("Voc� n�o pode inserir espa�o no in�cio do texto.");
			}
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(e.getMessage());
		}
	}

	public void gerarNovaSenha() {

	}

	public void limpar() {
		iniciarInterface();
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessageErro(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void enviarEmail() {
		buscarParametro();
		enviarEmail.enviaRecuperacaoEmail(emailUsuario, smtp, smtpPort, sslOn, emailRemetente, senhaRemetente,
				altenticacaoEmail);
	}

	public void buscarParametro() {
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

	public void usuarioLogado() {
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (usuarioLogado instanceof UserDetails) {
			username = ((UserDetails) usuarioLogado).getUsername();
		} else {
			username = usuarioLogado.toString();
		}
	}

}
