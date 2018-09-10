package br.com.mwork.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.mwork.RN.CadastroUsuarioRN;
import br.com.mwork.RN.CrudAtividadeRN;
import br.com.mwork.RN.PesquisaRN;
import br.com.mwork.entities.TbAtividade;
import br.com.mwork.entities.TbAtividadeUsuario;
import br.com.mwork.entities.TbDadosMinerado;
import br.com.mwork.entities.TbInformacoesUsuario;
import br.com.mwork.entities.ViewCidade;
import br.com.mwork.entities.ViewPrestador;
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
public class PesquisaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5000879910051331829L;

	@Inject
	PesquisaRN pesquisaRN;

	@Inject
	CadastroUsuarioRN cadastroUsuarioRN;

	@Getter
	@Setter
	private TbInformacoesUsuario prestador;

	@Getter
	@Setter
	private String descricao;

	@Getter
	@Setter
	private int valor;

	@Getter
	@Setter
	private int estadoSelecionado;

	@Getter
	@Setter
	private List<ViewPrestador> listaPrestadorCadastrado;

	@Getter
	@Setter
	private List<TbAtividadeUsuario> listaAtividadeUsuario;

	@Getter
	@Setter
	private TbAtividadeUsuario atividadeSelecionadaUsuario;

	@Getter
	@Setter
	private ViewPrestador prestadorSelecionado;

	@Getter
	@Setter
	private List<TbDadosMinerado> listaPestadorMinerado;

	@Getter
	@Setter
	private TbDadosMinerado prestadorMineradoSelecionado;

	@Getter
	@Setter
	public String atividade;

	@Getter
	@Setter
	private int cidadeSelecionada;

	@Getter
	@Setter
	private Map<String, Integer> cidades = new HashMap<String, Integer>();

	@Getter
	@Setter
	private List<ViewCidade> listaCidade;

	@Getter
	@Setter
	private List<ViewCidade> listaCidadeFiltrada;
	
	@Getter
	@Setter
	private boolean habilitaInputCidade;

	@PostConstruct
	public void iniciarInterface() {
		listaAtividadeUsuario = new ArrayList<TbAtividadeUsuario>();
		listaPrestadorCadastrado = new ArrayList<ViewPrestador>();
		listaPestadorMinerado = new ArrayList<TbDadosMinerado>();
		listaCidade = new ArrayList<ViewCidade>();
		listaCidadeFiltrada = new ArrayList<ViewCidade>();
		prestador = new TbInformacoesUsuario();
		controleInicial();
	}

	private void controleInicial() {
		atividade = "";
		estadoSelecionado = 0;
		cidadeSelecionada = 0;
		habilitaInputCidade = true;
	}

	public void buscarPrestador() {
		listaPrestadorCadastrado.clear();
		try {
			listaPrestadorCadastrado
					.addAll(pesquisaRN.buscarPrestador(atividade, estadoSelecionado, cidadeSelecionada));
		} catch (Exception e) {
			e.printStackTrace();
		}
		buscarPrestadorMinerado();
	}

	public void buscarPrestadorMinerado() {
		listaPestadorMinerado.clear();
		listaPestadorMinerado.addAll(pesquisaRN.buscarPrestadorMinerado(atividade));
	}

	public void avaliarPrestador() {
		try {

			pesquisaRN.avaliarPrestador(prestador);
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(e.getMessage());
		}
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

}
