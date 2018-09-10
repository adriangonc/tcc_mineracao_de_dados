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
import br.com.mwork.RN.CrudPrestadorRN;
import br.com.mwork.entities.Parametro;
import br.com.mwork.entities.TbEstado;
import br.com.mwork.entities.ViewCidade;
import br.com.mwork.entities.ViewPrestador;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Adriano
 *
 */
@ManagedBean
@ViewScoped
public class CrudPrestadorMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3212483617924036532L;

	@Inject
	CrudPrestadorRN crudPrestadorRN;

	@Inject
	CadastroUsuarioRN cadastroUsuarioRN;
	
	@Getter
	@Setter
	private Parametro parametro;

	@Getter
	@Setter
	private List<ViewPrestador> listaPrestador;
	
	@Getter
	@Setter
	private int estadoSelecionado;

	@Getter
	@Setter
	private String cidadeSelecionada;

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
		listaPrestador = new ArrayList<ViewPrestador>();
		parametro = new Parametro();
		controleInicial();
		listaCidade = new ArrayList<ViewCidade>();
		listaCidadeFiltrada = new ArrayList<ViewCidade>();
	}

	private void controleInicial() {
		habilitaInputCidade = true;
	}

	public void buscarPrestador() {
		try {
			listaPrestador.clear();
			listaPrestador.addAll(crudPrestadorRN.buscarPrestador(estadoSelecionado, cidadeSelecionada));
			
		} catch (Exception e) {
			e.printStackTrace();
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
