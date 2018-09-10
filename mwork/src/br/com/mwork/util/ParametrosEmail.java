package br.com.mwork.util;

import java.util.List;

import br.com.mwork.TO.ParametrosEmailTO;
import br.com.mwork.ejb.services.ParametroService;
import br.com.mwork.entities.Parametro;
import lombok.Getter;
import lombok.Setter;

public class ParametrosEmail {
	
	ParametroService parametroService;
	
	@Getter @Setter
	private ParametrosEmailTO objectoParametrosEmailTO;
	
	@Getter @Setter
	private List<Parametro> listaParametroSistema;
	
	
	public ParametrosEmailTO buscarParametroEmail() {
		objectoParametrosEmailTO = new ParametrosEmailTO();
		try {
			listaParametroSistema = parametroService.buscarParametro();

			for (Parametro parametro : listaParametroSistema) {
				if (parametro.getParametroId().equals(111)) {
					objectoParametrosEmailTO.setEmailRemetente(parametro.getParametroValor());
				}
				if (parametro.getParametroId().equals(116)) {
					objectoParametrosEmailTO.setSenhaRemetente(parametro.getParametroValor());
				}
				if (parametro.getParametroId().equals(115)) {
					objectoParametrosEmailTO.setAltenticacaoEmail(parametro.getParametroValor());
				}
				if (parametro.getParametroId().equals(113)) {
					objectoParametrosEmailTO.setSmtp(parametro.getParametroValor());
				}
				if (parametro.getParametroId().equals(112)) {
					objectoParametrosEmailTO.setSmtpPort(Integer.parseInt(parametro.getParametroValor()));
				}
				if (parametro.getParametroId().equals(114)) {
					if (parametro.getParametroValor().equals("true")) {
						objectoParametrosEmailTO.setSslOn(true);
					} else {
						objectoParametrosEmailTO.setSslOn(false);
					}
				}
			}
			return objectoParametrosEmailTO;
		} catch (Exception e) {
			e.printStackTrace();
			return objectoParametrosEmailTO;
		}
	}
}
