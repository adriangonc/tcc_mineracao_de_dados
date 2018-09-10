package br.com.mwork.RN;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.mwork.ejb.services.AtividadeService;
import br.com.mwork.entities.TbAtividade;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Adriano
 *
 */
// @ManagedBean
@RequestScoped
@Named
public class CrudAtividadeRN implements Serializable {

	private static final long serialVersionUID = 5935759574631158435L;

	@Getter
	@Setter
	private String descricao;

	@Getter
	@Setter
	private String usuario;

	@Getter
	@Setter
	private List<TbAtividade> listaAtividade;
	@EJB
	private AtividadeService service;

	@PostConstruct
	public void iniciarInterface() {
		listaAtividade = new ArrayList<TbAtividade>();
		buscarAtividade();
		controleInicial();
	}

	private void controleInicial(){
	}
	
	
	public List<TbAtividade> buscarAtividade() {
		listaAtividade.clear();
		try {
			listaAtividade = service.buscarAtividade();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaAtividade;
	}

	public void alterarAtividade(TbAtividade atividade) {
		try {
			service.alterarAtividade(atividade);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removerAtividade(TbAtividade atividade) {
		try {
			service.removerAtividade(atividade.getIdAtividade());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserirAtividade(TbAtividade atividade) {
		try {
			service.inserirAtividade(atividade);
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

}
