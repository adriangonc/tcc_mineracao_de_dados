package br.com.mwork.RN;

import java.io.Serializable;
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

import br.com.mwork.ejb.services.CidadeService;
import br.com.mwork.ejb.services.EstadoService;
import br.com.mwork.ejb.services.UsuarioService;
import br.com.mwork.entities.Parametro;
import br.com.mwork.entities.TbCidade;
import br.com.mwork.entities.TbEstado;
import br.com.mwork.entities.TbInformacoesUsuario;
import br.com.mwork.entities.User;
import br.com.mwork.entities.ViewCidade;
import br.com.mwork.util.EnviarEmailUtil;
import br.com.mwork.util.ValorFixo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Adriano
 * @since 02/05/2016
 */
@RequestScoped
@Named
public class CadastroUsuarioRN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7129461744451090768L;

	@Getter
	@Setter
	private List<User> listaUsuario;

	@Getter
	@Setter
	private List<TbEstado> listaEstados;

	@Getter
	@Setter
	private List<ViewCidade> listaCidade;

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
	private EstadoService serviceEstado;

	@EJB
	private CidadeService serviceCidade;

	@EJB
	private UsuarioService serviceUsuario;

	@Getter
	@Setter
	private User usuario;
	
	@Getter
	@Setter
	private TbInformacoesUsuario informacoesUsuario;

	@PostConstruct
	public void iniciarInterface() {
		listaParametroSistema = new ArrayList<Parametro>();
		listaEstados = new ArrayList<TbEstado>();
		listaCidade = new ArrayList<ViewCidade>();
		listaUsuario = new ArrayList<User>();
		enviarEmail = new EnviarEmailUtil();
		usuario = new User();
		informacoesUsuario = new TbInformacoesUsuario();
		controleInicial();
	}

	private void controleInicial() {
	}

	public List<TbEstado> buscarEstado() {
		listaEstados.clear();
		try {
			listaEstados = serviceEstado.buscarEstado();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaEstados;
	}

	public List<ViewCidade> buscarCidade() {
		listaCidade.clear();
		try {
			listaCidade = serviceCidade.buscarCidade();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCidade;
	}
	
	public User buscarUsuario(String usename) {
		usuario = new User();
		try {
			usuario = serviceUsuario.buscarUsuarioPorId(usename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public TbInformacoesUsuario buscarInformacoesUsuario(int id) {
		informacoesUsuario = new TbInformacoesUsuario();
		try {
			informacoesUsuario = serviceUsuario.buscarUsuarioSistemaPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return informacoesUsuario;
	}
	
	public TbInformacoesUsuario atualizarUsuario(User usuario, TbInformacoesUsuario informacoesUsuario) {
		try {
			serviceUsuario.atualizarUsuario(usuario, informacoesUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return informacoesUsuario;
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

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessageErro(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public boolean inserirUsuarioDoSistema(User usuario, TbInformacoesUsuario usuarioDoSistema) {
		try {
			usuario.setRole(ValorFixo.ROLE_USUARIO_DO_SISTEMA);
			serviceUsuario.inserirUsuarioDoSistema(usuario, usuarioDoSistema);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
