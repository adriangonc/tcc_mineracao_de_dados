package br.com.mwork.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.mwork.RN.CrudAtividadeRN;
import br.com.mwork.entities.TbAtividade;
import br.com.mwork.entities.TbAtividadeUsuario;
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
public class CrudAtividadeMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7210388486782656409L;

	@Inject
	CrudAtividadeRN crudAtividadeRN;

	@Getter
	@Setter
	private TbAtividade atividade;

	@Getter
	@Setter
	private String descricao;

	@Getter
	@Setter
	private int valor;

	@Getter
	@Setter
	private List<TbAtividade> listaAtividade;
	
	@Getter
	@Setter
	private List<TbAtividadeUsuario> listaAtividadeUsuario;
	
	@Getter @Setter
	private TbAtividadeUsuario atividadeSelecionadaUsuario;

	@Getter
	@Setter
	private TbAtividade atividadeSelecionada;

	@PostConstruct
	public void iniciarInterface() {
		listaAtividadeUsuario = new ArrayList<TbAtividadeUsuario>();
		listaAtividade = new ArrayList<TbAtividade>();
		atividadeSelecionada = new TbAtividade();
		atividade = new TbAtividade();
		buscarAtividade();
		controleInicial();
	}

	private void controleInicial() {
	}

	public void buscarAtividade() {
		try {
			listaAtividade.clear();
			listaAtividade.addAll(crudAtividadeRN.buscarAtividade());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterarAtividade() {
		try {

			if (ValidadorUtil.verificaInsesaoEspaco(atividadeSelecionada.getNomeAtividade())) {
				crudAtividadeRN.alterarAtividade(atividadeSelecionada);
				addMessage(ValorFixo.MSG_REGISTRO_ALTERADO);
			} else {
				addMessageErro(ValorFixo.MSG_ERRO_ESPACO);
			}
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(e.getMessage());
		}
	}

	public void removerAtividade() {
		try {
			crudAtividadeRN.removerAtividade(atividadeSelecionada);
			buscarAtividade();
			addMessage(ValorFixo.MSG_REGISTRO_EXCLUIDO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserirAtividade() {
		try {

			if (ValidadorUtil.verificaInsesaoEspaco(atividadeSelecionada.getNomeAtividade())) {
				crudAtividadeRN.inserirAtividade(atividadeSelecionada);
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
