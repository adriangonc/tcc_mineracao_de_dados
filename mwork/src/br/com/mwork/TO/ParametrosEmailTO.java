package br.com.mwork.TO;

import lombok.Data;

public @Data class ParametrosEmailTO {
	private String emailRemetente;
	private String senhaRemetente;
	private String altenticacaoEmail;
	private String smtp;
	private Integer smtpPort;
	private boolean sslOn;
}
