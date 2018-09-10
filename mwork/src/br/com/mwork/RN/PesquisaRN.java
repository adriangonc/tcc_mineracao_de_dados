package br.com.mwork.RN;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.mwork.ejb.services.PesquisaPrestadorService;
import br.com.mwork.entities.TbDadosMinerado;
import br.com.mwork.entities.TbInformacoesUsuario;
import br.com.mwork.entities.ViewPrestador;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Adriano
 *
 */
@RequestScoped
@Named
public class PesquisaRN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4285642959296024762L;

	@Getter
	@Setter
	private String descricao;

	@Getter
	@Setter
	private String usuario;

	@Getter
	@Setter
	private TbInformacoesUsuario prestador;

	@Getter
	@Setter
	private List<ViewPrestador> listaPrestadorCadastrado;

	@Getter
	@Setter
	private List<ViewPrestador> listaPrestadorCadastradoAux;

	@Getter
	@Setter
	private List<TbDadosMinerado> listaPestadorMinerado;

	@Getter
	@Setter
	private List<TbDadosMinerado> listaPestadorMineradoAux;

	@EJB
	private PesquisaPrestadorService service;

	@PostConstruct
	public void iniciarInterface() {
		listaPrestadorCadastrado = new ArrayList<ViewPrestador>();
		listaPrestadorCadastradoAux = new ArrayList<ViewPrestador>();
		listaPestadorMinerado = new ArrayList<TbDadosMinerado>();
		listaPestadorMineradoAux = new ArrayList<TbDadosMinerado>();
		controleInicial();
		prestador = new TbInformacoesUsuario();
	}

	private void controleInicial() {
	}

	public List<ViewPrestador> buscarPrestador(String atividade, int estado, int cidade) {
		listaPrestadorCadastrado.clear();
		listaPrestadorCadastradoAux.clear();
		try {
			listaPrestadorCadastrado = service.buscarPrestador(prestador);
			for (ViewPrestador prestador : listaPrestadorCadastrado) {
				if (prestador.getDescricaoAtividades().toLowerCase().contains(atividade.toLowerCase())
						&& (prestador.getCodEstado() == estado || estado == 0)
						&& (prestador.getCodIbgeCidade() == cidade || cidade == 0)) {
					listaPrestadorCadastradoAux.add(prestador);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaPrestadorCadastradoAux;
	}

	public void avaliarPrestador(TbInformacoesUsuario prestador) {
		try {
			service.avaliarPrestador(prestador);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<TbDadosMinerado> buscarPrestadorMinerado(String atividade) {
		listaPestadorMinerado.clear();
		listaPestadorMineradoAux.clear();

		try {
			listaPestadorMinerado.addAll(service.buscarPrestadorMinerado(atividade));
			for (TbDadosMinerado dados : listaPestadorMinerado) {
				if (dados.getTitulo().toLowerCase().contains(atividade.toLowerCase())) {
					listaPestadorMineradoAux.add(dados); // FIXME Otimizar
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaPestadorMineradoAux;

	}

	public Calendar dataHoraAtual() {
		Calendar data = Calendar.getInstance();
		return data;
	}

	public void limparTela() {
		iniciarInterface();
	}

}
