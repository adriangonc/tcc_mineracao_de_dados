package br.com.mwork.util;

import java.math.BigDecimal;

public class ValorFixo {

	/* DESCRICAO ERRO */
	public static final String MSG_ERRO_QUANTIDADE_INCORRETA = "Quantidade do Trailler do Arquivo Incorreta";
	public static final String MSG_ERRO_ESPACO = "N�o � poss�vel inserir espa�o no in�cio do texto.";

	public static final String CANCELADA = "C";
	public static final String EFETIVADA = "E";
	public static final String PENDENTE_ENVIO_INVOICE = "P";

	public static final int TIPO_TRANSACAO_DEPOSITO_IDENTIFICADO = 2;
	public static final int TIPO_TRANSACAO_DOC_TED = 3;
	public static final int TIPO_TRANSACAO_CREDITO_EM_CONTA = 4;
	public static final int CARTAO_CREDITO = 11;

	/* SOLICITANTE */
	public static final String SISTEMA = "Sistema";
	public static final String API = "API";

	/* SITUACAO AGENDAMENTO */
	public static final String NAO_PAGO = "01";
	public static final String PAGO = "02";

	/* PARAMETRO */

	/* ROTINA */
	public static final String INICIO_DA_EXECUCAO_DA_ROTINA = "[ROTINA]=> Início da execução: ";
	public static final String FIM_DA_EXECUCAO_DA_ROTINA = "[ROTINA]=> Fim da execução: ";

	/* VALOR PADRAO COMBO */
	public static final String SELECIONE = "selecione";
	public static final String ROLE_USUARIO_DO_SISTEMA = "ROLE_USUARIO";

	/* MOEDA */
	public static final String REAL = "R$ ";

	public static final int PAGAMENTOS_LIQUIDADOS = 1;
	public static final int PAGAMENTOS_EXPIRADOS = 2;

	/* RESPOSTA */
	public static final String SIM = "S";
	public static final String NAO = "N";

	/* VALOR */
	public static final BigDecimal ZERO = new BigDecimal(0.00);
	public static final String VAZIO = "";

	/* TIPO DE CONTA */
	public static final int CONTA_CORRENTE_INDIVIDUAL = 01;

	/* MENSAGENS */
	public static final String MSG_REGISTRO_EXCLUIDO = "Registro excluido com sucesso.";
	public static final String MSG_REGISTRO_ALTERADO = "Registro alterado com sucesso.";
	public static final String MSG_REGISTRO_SALVO = "Registro salvo com sucesso.";
	public static final String MSG_INICIO_MINERACAO = "O processo de minera��o foi iniciado, este processo pode levar muito tempo. Ser� emitido um aviso no fim da execu��o!";
	public static final String MSG_FIM_MINERACAO = "Minera��o de dados concluida!.";
	public static final String MSG_ERRO_SALVAR = "Erro ao salvar registro, tente novamente!.";
	
	
	/*TIPO TRANSACAO FINANCEIRA */
	public static final int TIPO_ENVENTO_COMPRA_CARTAO = 43;
	
	/*SIGLAS*/
	public static final String SIGLA_CNPJ = "CNPJ:";
	public static final String SIGLA_CEP = "CEP:";
	
}
