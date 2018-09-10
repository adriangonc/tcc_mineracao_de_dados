package br.com.mwork.RN;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.mwork.ejb.services.BuscaPrestadorService;
import br.com.mwork.entities.Parametro;
import br.com.mwork.entities.ViewPrestador;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Adriano
 *
 */
@RequestScoped
@Named
public class CrudPrestadorRN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5244769516569115969L;

	@Getter
	@Setter
	private List<ViewPrestador> listaPrestador;
	@EJB
	private BuscaPrestadorService service;

	@Getter
	@Setter
	private Parametro parametroSelecionado;

	@PostConstruct
	public void iniciarInterface() {
		listaPrestador = new ArrayList<ViewPrestador>();
		controleInicial();
	}

	private void controleInicial() {
	}

	public List<ViewPrestador> buscarPrestador(int estadoSelecionado, String cidadeSelecionada) {
		try {
			listaPrestador = service.buscarPrestador(estadoSelecionado, cidadeSelecionada);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaPrestador;
	}

	public Calendar dataHoraAtual() {
		Calendar data = Calendar.getInstance();
		return data;
	}

	public void limparTela() {
		iniciarInterface();
	}

}
