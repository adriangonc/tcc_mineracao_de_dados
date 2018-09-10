package br.com.mwork.RN;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.mwork.ejb.services.MineracaoService;
import br.com.mwork.entities.TbDadosMinerado;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Adriano
 *
 */
@RequestScoped
@Named
//@Stateless
public class MinerarDadosRN implements Serializable {

	private static final long serialVersionUID = 6174967165362832069L;

	@Getter
	@Setter
	private String descricao;
	
	private MineracaoService mineracaoService;

	@Getter
	@Setter
	private String usuario;

	@Getter
	@Setter
	private List<TbDadosMinerado> listaDadosMinerados;
	
	@EJB
	private MineracaoService service;
	
	@Inject
	private EntityManager em;

	@PostConstruct
	public void iniciarInterface() {
		listaDadosMinerados = new ArrayList<TbDadosMinerado>();
		controleInicial();
	}
	
	
	/*public void iniciarServicos() throws NamingException {
		InitialContext ic = new InitialContext();
		//transacaoService = (Transacao) ic.lookup("java:global/billing/TransacaoService!br.com.billing.ejb.services.Transacao");
		mineracaoService = (MineracaoService) ic.lookup("java:global/mwork/MineracaoDadosServiceJPADao!br.com.mwork.ejb.services.MineracaoService");
	}*/

	private void controleInicial(){
	}
	
	
	public List<TbDadosMinerado> buscarAtividade() {
		listaDadosMinerados.clear();
		try {
			listaDadosMinerados = service.buscarDadosMinerados();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaDadosMinerados;
	}

	public void inserirDadosMinerados(TbDadosMinerado dados) {
		try {
			service.inserirDadosMinerados(dados);
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
