package br.com.mwork.RN;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.mwork.ejb.services.ParametroService;
import br.com.mwork.entities.Parametro;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Adriano
 *
 */
// @ManagedBean
@RequestScoped
@Named
public class CrudParametroNegocioRN implements Serializable {

	private static final long serialVersionUID = -335284558563835497L;
	@Getter
	@Setter
	private Parametro parametro;

	@Getter
	@Setter
	private String descricao;

	@Getter
	@Setter
	private String usuario;

	@Getter
	@Setter
	private int valor;

	@Getter
	@Setter
	private List<Parametro> listaParametro;
	@EJB
	private ParametroService service;

	@Getter
	@Setter
	private Parametro parametroSelecionado;

	@PostConstruct
	public void iniciarInterface() {
		listaParametro = new ArrayList<Parametro>();
		parametroSelecionado = new Parametro();
		parametro = new Parametro();
		buscarParametro();
		controleInicial();
	}

	private void controleInicial(){
		parametro.setParametroTipo(2);
	}
	
	
	public List<Parametro> buscarParametro() {
		try {
			listaParametro = service.buscarParametro();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaParametro;
	}

	public void alterarParametro(Parametro parametroSelecionado) {
		try {
			parametroSelecionado.setDataAlteracao(dataHoraAtual());
			parametroSelecionado.setUsuarioAlteracao(usuarioSistema());
			service.alterarParametro(parametroSelecionado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removerParametro(Parametro parametroSelecionado) {
		try {
			service.removerParametro(parametroSelecionado.getParametroId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserirParametro(Parametro parametro) {
		try {
			parametro.setUsuarioInclusao(usuarioSistema());
			parametro.setDataInclusao(dataHoraAtual());
			service.inserirParametro(parametro);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Calendar dataHoraAtual() {
		Calendar data = Calendar.getInstance();
		return data;
	}

	private String usuarioSistema() {
		setUsuario("adriangonc");
		return usuario;
	}

	public void limparTela() {
		iniciarInterface();
	}

}
