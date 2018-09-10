package br.com.mwork.util;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.mwork.TO.ParametrosEmailTO;
import br.com.mwork.entities.Parametro;
import lombok.Getter;
import lombok.Setter;

public class EnviarEmailAnexo implements Serializable {

	private static final long serialVersionUID = 6452099787668443112L;

	@Getter
	@Setter
	private List<Parametro> listaParametro;

	@Getter
	@Setter
	private Parametro parametro;

	@Getter
	@Setter
	private String textoEmail;

	@Getter
	@Setter
	private Date dataAtual;

	public void enviarEmailComAnexo(String emailUsuario, ParametrosEmailTO parametrosEmail,
			String urlArquivoCashBackCupom, String LinkBoleto) {
		textoEmail = "";
		montarCorpoEmail(urlArquivoCashBackCupom, LinkBoleto);

		try {

			SimpleEmail email = new SimpleEmail();
			email.setHostName(parametrosEmail.getSmtp());
			email.setSmtpPort(parametrosEmail.getSmtpPort());
			if (parametrosEmail.getAltenticacaoEmail().equals("true")) {
				email.setAuthenticator(new DefaultAuthenticator(parametrosEmail.getEmailRemetente(),
						parametrosEmail.getSenhaRemetente()));
			}
			email.setSSLOnConnect(parametrosEmail.isSslOn());
			email.setFrom(parametrosEmail.getEmailRemetente(), "Mwork Services");
			email.setSubject("Faturas QUI");
			email.setMsg(textoEmail);
			email.addTo(emailUsuario);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	private String montarCorpoEmail(String urlArquivoCashBackCupom, String LinkBoleto) {
		dataAtual = new Date();
		String usuario = " ";

		textoEmail = String.format("Olá " + usuario + "," + "\nSegue link da fatura para pagamento:\n"
				+ urlArquivoCashBackCupom + "\n" + LinkBoleto);
		return textoEmail;
	}

}
