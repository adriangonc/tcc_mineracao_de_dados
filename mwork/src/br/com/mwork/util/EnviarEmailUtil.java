package br.com.mwork.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.mwork.RN.CrudParametroNegocioRN;
import br.com.mwork.ejb.services.ParametroService;
import br.com.mwork.entities.Parametro;
import lombok.Getter;
import lombok.Setter;

@RequestScoped
@Named
public class EnviarEmailUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6452099787668443112L;

	@Getter
	@Setter
	private List<Parametro> listaParametro;

	@Getter
	@Setter
	private Parametro parametro;

	@Inject
	CrudParametroNegocioRN crudParametroNegocioRN;

	@Getter
	@Setter
	private String textoEmail;

	@Getter
	@Setter
	private Date dataAtual;

	@EJB
	private ParametroService service;

	@PostConstruct
	public void init() {
		dataAtual = new Date();
		parametro = new Parametro();
		listaParametro = new ArrayList<Parametro>();
		crudParametroNegocioRN = new CrudParametroNegocioRN();
	}

	public void controleInicial() {

	}

	public String enviaRecuperacaoEmail(String emailUsuario, String smtp, Integer smtpPort, Boolean sslOn,
			String remetente, String senha, String altenticacaoEmail) {
		textoEmail = "";
		String senhaAleatoria = gerarSenhaAleatoria();
		montarEmailRecuperacao(senhaAleatoria);

		try {
			SimpleEmail email = new SimpleEmail();
			email.setHostName(smtp);
			email.setSmtpPort(smtpPort);
			if (altenticacaoEmail.equals("true")) {
				email.setAuthenticator(new DefaultAuthenticator(remetente, senha));
			}
			email.setSSLOnConnect(sslOn); // disable in case of EmailException
			email.setFrom(remetente, "Mwork Sistem");
			email.setSubject("Recuperação de senha Mwork");
			email.setMsg(textoEmail);
			email.addTo(emailUsuario);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return senhaAleatoria;
	}

	public String enviaEmailNovoUsuario(String emailUsuario, String smtp, Integer smtpPort, Boolean sslOn,
			String remetente, String senha, String altenticacaoEmail) {
			textoEmail = "";
			String senhaAleatoria = gerarSenhaAleatoria();
			montarEmailNovoUsuario(senhaAleatoria, emailUsuario);
	
			try {
				SimpleEmail email = new SimpleEmail();
				email.setHostName(smtp);
				email.setSmtpPort(smtpPort);
				if (altenticacaoEmail.equals("true")) {
					email.setAuthenticator(new DefaultAuthenticator(remetente, senha));
				}
				email.setSSLOnConnect(sslOn); // disable in case of EmailException
				email.setFrom(remetente, "Mwork Sistem");
				email.setSubject("Recuperação de senha Mwork");
				email.setMsg(textoEmail);
				email.addTo(emailUsuario);
				email.send();
			} catch (EmailException e) {
				e.printStackTrace();
			}
			return senhaAleatoria;
		}

	private String montarEmailRecuperacao(String senhaGerada) {
		dataAtual = new Date();
		String usuario = " ";
		textoEmail = String.format("Olá %s. \n" + "Foi solicitada a recuperação de senha para seu usuário. \n"
				+ "Geramos uma nova senha pra você. \n" + "Use esta nova senha para entrar no sistema. \n\n"
				+ "Senha: %s \n\n" + "Data da solicitação: %s ", usuario, senhaGerada, dataAtual);
		return textoEmail;
	}

	private String montarEmailNovoUsuario(String senhaGerada, String email) {
		dataAtual = new Date();
		String usuario = " ";
		
		textoEmail = String.format(
				"Olá %s. \n" + "Seja bem vindo ao Mwork Sinstem. \n" + "Foi criado um usuário pra você. \n"
						+ "Entre com seu email: %s e senha. \n" + "Geramos uma nova senha pra você. \n"
						+ "Use esta senha para entrar no sistema. \n\n" + "Senha: %s \n\n" + "Data da solicitaçao: %s ",
				usuario, email, senhaGerada, dataAtual);
		return textoEmail;
	}

	public String gerarSenhaAleatoria() {
		String[] carct = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
				"Y", "Z", "!", "#", "&", "$", "*" };

		String senha = "";

		for (int x = 0; x < 8;) {
			int j = (int) (Math.random() * carct.length);
			senha += carct[j];
			x++;
		}
		return senha;
	}
}
