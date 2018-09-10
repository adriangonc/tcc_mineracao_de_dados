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

import br.com.mwork.RN.CrudParametroNegocioRN;
import br.com.mwork.ejb.services.ParametroService;
import br.com.mwork.entities.Parametro;
import br.com.mwork.util.ValidadorUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Adriano
 *
 */
@ManagedBean
@ViewScoped
public class CrudParametrosSistemaMB implements Serializable {

	@Inject
	CrudParametroNegocioRN crudParametroNegocioRN;

	private static final long serialVersionUID = -335284558563835497L;
	@Getter
	@Setter
	private Parametro parametro;

	@Inject
	private ValidadorUtil validadorUtil;

	@Getter
	@Setter
	private String descricao;

	@Getter
	@Setter
	private int valor;

	@Getter
	@Setter
	private List<Parametro> listaParametro;
	
	@Getter
	@Setter
	private List<Parametro> listaParametroSistema;

	@Getter
	@Setter
	private List<Parametro> listaParametroNegocio;
	
	@EJB
	private ParametroService service;

	@Getter
	@Setter
	private Parametro parametroSelecionado;

	@PostConstruct
	public void iniciarInterface() {
		listaParametro = new ArrayList<Parametro>();
		listaParametroSistema = new ArrayList<Parametro>();
		listaParametroNegocio = new ArrayList<Parametro>();
		parametroSelecionado = new Parametro();
		parametro = new Parametro();
		buscarParametro();
		controleInicial();
	}

	private void controleInicial() {
		parametro.setParametroTipo(1);
	}

	public void buscarParametro() {
		try {
			listaParametro.clear();
			listaParametroSistema.clear();
			listaParametro.addAll(crudParametroNegocioRN.buscarParametro());
			for (Parametro parametro : listaParametro) {
				if (parametro.getParametroTipo() == 1) {
					listaParametroSistema.add(parametro);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterarParametro() {
		try {

			if (validadorUtil.verificaInsesaoEspaco(parametroSelecionado.getParametroDescricao())
					&& validadorUtil.verificaInsesaoEspaco(parametroSelecionado.getParametroValor())) {
				crudParametroNegocioRN.alterarParametro(parametroSelecionado);
				addMessage("Registro alterado com sucesso.");
			} else {
				addMessageErro("Voc� n�o pode inserir espa�o no in�cio do texto.");
			}
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(e.getMessage());
		}
	}

	public void removerParametro() {
		try {
			crudParametroNegocioRN.removerParametro(parametroSelecionado);
			buscarParametro();
			addMessage("Registro excluido com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserirParametro() {
		try {

			if (validadorUtil.verificaInsesaoEspaco(parametro.getParametroDescricao())
					&& validadorUtil.verificaInsesaoEspaco(parametro.getParametroValor())) {
				crudParametroNegocioRN.inserirParametro(parametro);
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

}
