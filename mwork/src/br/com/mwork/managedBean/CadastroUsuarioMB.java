package br.com.mwork.managedBean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mwork.RN.CadastroUsuarioRN;
import br.com.mwork.RN.CrudParametroNegocioRN;
import br.com.mwork.RN.CrudUsuarioRN;
import br.com.mwork.ejb.services.CidadeService;
import br.com.mwork.ejb.services.EstadoService;
import br.com.mwork.entities.Parametro;
import br.com.mwork.entities.TbCidade;
import br.com.mwork.entities.TbEstado;
import br.com.mwork.entities.TbInformacoesUsuario;
import br.com.mwork.entities.User;
import br.com.mwork.entities.ViewCidade;
import br.com.mwork.util.EnviarEmailUtil;
import br.com.mwork.util.ValidadorUtil;
import br.com.mwork.util.ValorFixo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Adriano
 *
 */
@ManagedBean
@ViewScoped
public class CadastroUsuarioMB implements Serializable {

	private static final long serialVersionUID = -2966963847159796228L;

	@Inject
	CrudUsuarioRN crudUsuarioRN;

	@Inject
	ValidadorUtil validadorUtil;

	@Getter
	@Setter
	private User usuario;

	@Inject
	CrudParametroNegocioRN crudParametroRN;

	@Inject
	CadastroUsuarioRN cadastroUsuarioRN;

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

	@Getter
	@Setter
	private User usuarioSelecionado;

	@Getter
	@Setter
	private TbInformacoesUsuario informacoesUsuarioSistema;

	@Getter
	@Setter
	private TbEstado estado;

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
	private List<TbEstado> listaEstados;

	@Getter
	@Setter
	private List<ViewCidade> listaCidade;

	@Getter
	@Setter
	private List<ViewCidade> listaCidadeFiltrada;

	@EJB
	private EstadoService serviceEstado;

	@EJB
	private CidadeService serviceCidade;

	@Getter
	@Setter
	private Parametro parametroSelecionado;

	@Getter
	@Setter
	private Boolean gerarNovaSenha;

	@Getter
	@Setter
	private EnviarEmailUtil enviarEmail;

	@Getter
	@Setter
	private String username;

	@Getter
	@Setter
	private int estadoSelecionado;

	@Getter
	@Setter
	private Map<String, Integer> estados = new HashMap<String, Integer>();

	@Getter
	@Setter
	private Integer cidadeSelecionada;

	@Getter
	@Setter
	private String ePrestador;

	@Getter
	@Setter
	private Map<String, Integer> cidades = new HashMap<String, Integer>();

	@Getter
	@Setter
	private boolean habilitaInputCidade;

	@Getter
	@Setter
	private boolean habilitaDescricao;

	@PostConstruct
	public void iniciarInterface() {
		listaEstados = new ArrayList<TbEstado>();
		listaCidade = new ArrayList<ViewCidade>();
		listaCidadeFiltrada = new ArrayList<ViewCidade>();
		listaUsuario = new ArrayList<User>();
		parametroSelecionado = new Parametro();
		enviarEmail = new EnviarEmailUtil();
		usuario = new User();
		usuarioSelecionado = new User();
		informacoesUsuarioSistema = new TbInformacoesUsuario();
		estado = new TbEstado();
		controleInicial();
	}

	private void controleInicial() {
		setGerarNovaSenha(false);
		usuarioLogado();
		buscarEstado();
		habilitaInputCidade = true;
		habilitaDescricao = true;
		ePrestador = "N";
	}

	public void buscarUsuario() {
		try {
			listaUsuario.clear();
			listaUsuario.addAll(crudUsuarioRN.buscarUsuario());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buscarEstado() {
		try {
			listaEstados.clear();
			listaEstados.addAll(cadastroUsuarioRN.buscarEstado());

			estados = new HashMap<String, Integer>();
			for (TbEstado tbEstado : listaEstados) {
				estados.put(tbEstado.getNomeEstado(), tbEstado.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void filtraCidadePorEstado() {
		buscarCidade();
		try {
			cidades = new LinkedHashMap<String, Integer>();
			listaCidadeFiltrada.clear();
			for (ViewCidade tbCidade : listaCidade) {
				if (tbCidade.getUfid().equals(estadoSelecionado)) {
					listaCidadeFiltrada.add(tbCidade);
				}
			}

			cidades.clear();
			for (ViewCidade cidade : listaCidadeFiltrada) {
				cidades.put(cidade.getNome(), cidade.getCodigoibge());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buscarCidade() {
		try {
			listaCidade.clear();
			listaCidade.addAll(cadastroUsuarioRN.buscarCidade());

			cidades = new LinkedHashMap<String, Integer>();
			for (ViewCidade cidade : listaCidade) {
				cidades.put(cidade.getNome(), cidade.getCodigoibge());
			}
			habilitaInputCidade = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removerUsuario() {
		crudUsuarioRN.removerUsuario(usuarioSelecionado);
		limpar();
	}

	public void inserirUsuarioDoSistema() {
		informacoesUsuarioSistema.setCodEstado(estadoSelecionado);
		informacoesUsuarioSistema.setCodIbgeCidade(cidadeSelecionada);
		//informacoesUsuarioSistema.setPrestador(ValorFixo.SIM);
		try {
			usuario.setPassword(crudUsuarioRN.criptografarSenha(usuarioSelecionado.getPassword()));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			if ((validadorUtil.verificaInsesaoEspaco(usuario.getEmail())
					&& validadorUtil.verificaInsesaoEspaco(usuario.getName()))) {
				cadastroUsuarioRN.inserirUsuarioDoSistema(usuario, informacoesUsuarioSistema);
				addMessage(ValorFixo.MSG_REGISTRO_SALVO);
			} else {
				addMessageErro(ValorFixo.MSG_ERRO_ESPACO);
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

	public void usuarioLogado() {
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (usuarioLogado instanceof UserDetails) {
			username = ((UserDetails) usuarioLogado).getUsername();
		} else {
			username = usuarioLogado.toString();
		}
	}

	public void habilitaDescricaoPrestador() {
		if (informacoesUsuarioSistema.getPrestador().equals(ValorFixo.SIM)) {
			habilitaDescricao = false;
		} else {
			habilitaDescricao = true;
		}
	}

	// finaliza sessão do usuário
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}

	public String buscarContext() {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		return servletContext.getRealPath("/relatorio/");
	}

}
