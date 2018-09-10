package br.com.mwork.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.mwork.entities.Parametro;

/**
 * @author Adriano
 * @since 12/04/2016
 *
 */
@RequestScoped
@Named
public class ValidadorUtil implements Serializable {

	private static final long serialVersionUID = -5989713864959883861L;

	/**
	 * @author Adriano
	 * @since 12/04/2016 Verifica a existencia de espa�o em branco na primeira
	 *        posi��o da string.
	 * 
	 */
	public static boolean verificaInsesaoEspaco(String str) {
		int count = 0;
		String spaco = " ";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(0) != spaco.charAt(0)) {
				count++;
			}
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @author Adriano
	 * @since 12/04/2016 Compara os par�metros do objeto com a lista, a fim de
	 *        verificar a ocorr�ncia de par�metros repetidos.
	 * 
	 */
	public boolean validarObjetoCadastrado(List<Parametro> lista, Parametro objeto) {
		boolean verificacao;
		verificacao = true;
		for (int i = 0; i < lista.size(); i++) {
			// objeto = lista.get(i);
			if (objeto.getParametroDescricao().equals(lista.get(i).getParametroDescricao())
					&& objeto.getParametroValor().equals(lista.get(i).getParametroValor())) {
				verificacao = false;
			}
		}
		return verificacao;
	}

	/**
	 * @author Adriano
	 * @since 12/04/2016 Compara os par�metros do objeto com a lista, a fim de
	 *        verificar a ocorr�ncia de par�metros repetidos.
	 * 
	 */
	public boolean validarObjetoAlterado(List<Parametro> lista, Parametro objeto) {
		boolean verificacao;
		int cont;
		cont = 0;
		verificacao = true;
		for (int i = 0; i < lista.size(); i++) {
			// objeto = lista.get(i);
			if (objeto.getParametroDescricao().equals(lista.get(i).getParametroDescricao())
					&& objeto.getParametroValor().equals(lista.get(i).getParametroValor())) {
				cont++;
			}
		}
		if (cont > 1) {
			verificacao = false;
		}
		return verificacao;
	}

	/**
	 * @author Matheus Carolino
	 * @since 29/04/2016 Recebe a data, e retorna somente a data no formato
	 *        yyyyMMdd
	 * 
	 */
	public static int transformarDataNoFormatoYYYYMMDD(Date data) {
		SimpleDateFormat fmtData = new SimpleDateFormat("yyyyMMdd");
		return Integer.valueOf(fmtData.format(data));
	}

	/**
	 * @author Matheus Carolino
	 * @since 29/04/2016 Recebe a data, e retorna somente a hora no formato
	 *        HHmmss
	 * 
	 */
	public static int transformarHoraNoFormatoHHMMSS(Date data) {
		SimpleDateFormat fmtHora = new SimpleDateFormat("HHmmss");
		return Integer.valueOf(fmtHora.format(data));
	}

	/**
	 * @author Matheus Carolino
	 * @throws ParseException
	 * @since 29/04/2016 Recebe a data, hora e retorna Date no formato
	 *        "ddMMyyyy HHmmss"
	 * 
	 */
	public static Date transformarStringEmDataHora(String DataString, String horaString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy HHmmss");
		return sdf.parse(DataString.concat(" ").concat(horaString));
	}

	/**
	 * @author Matheus Carolino
	 * @throws ParseException
	 * @since 29/04/2016 Recebe a data, e retorna Date no formato "ddMMyyyy"
	 * 
	 */
	public static Date transformarStringEmDataDDMMYYYY(String DataString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy"); 
		return sdf.parse(DataString);
	}
	
	/**
	 * @author Adriano
	 * @throws ParseException
	 * @since 29/04/2016 Recebe a data, e retorna Date no formato "ddMMyyyy"
	 * 
	 */
	public static Timestamp transformarStringEmTimestampDDMMYYYY(String DataString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy"); 
		return (Timestamp) sdf.parse(DataString);
	}

	/**
	 * @author Matheus Carolino
	 * @throws ParseException
	 * @since 29/04/2016 Recebe a data, e retorna Date no formato "ddMMyy"
	 * 
	 */
	public static Date transformarStringEmDataDDMMYY(String DataString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		return sdf.parse(DataString);
	}

	/**
	 * @author Mateus
	 * @since 03/05/2016 Remove os espa�os em branco do campo
	 * 
	 */
	public static String removerEspacoEmBranco(String text) {
		String string = text.trim();
		while (string.contains("  ")) {
			string = string.replaceAll("  ", " ");
		}
		return string;
	}

	/**
	 * @author Mateus
	 * @since 07/05/2016 Transforma String em BigDecimal
	 * 
	 */
	public static BigDecimal transformarStringEmBigDecimal(String valorString) {
		if (valorString == null || valorString.isEmpty()) {
			return new BigDecimal(0);
		} else {
			return new BigDecimal(acrescentarPontoValorString(valorString));
		}
	}

	/**
	 * @author Mateus
	 * @since 07/05/2016 Adicionar ponto em um valor String que j� existe dois
	 *        valores a direita correspondentes as casas decimais Ex: Entrada ->
	 *        String 2300 / Saida -> 23.00 Entrada -> String 2350 / Saida ->
	 *        23.50
	 * 
	 */
	private static String acrescentarPontoValorString(String string) {
		int tamanho;
		tamanho = string.length();
		string = string.substring(0, tamanho - 2).concat(".").concat(string.substring(tamanho - 2));
		return string;
	}

	public static Boolean ehNullo(Object obj) {
		if (obj == null) {
			return true;
		} else {
			return false;
		}
	}

	public static String transformarDataNoFormatoYYYY_MM_DD(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * @author Adriano
	 * @since 19/05/2016 Converte data para o formato 'dd/MM/yyyy'
	 * 
	 */
	public static String transformarDataNoFormatoDDmmYYYY(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	/**
	 * @author Matheus H
	 * @since 24/05/2016 Converte data para o formato 'dd/MM/yyyy HHmmss'
	 * 
	 */
	public static String transformarDataNoFormatoDDmmYYYYHHmmss(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf.format(date);
	}

	public static void iniciarRotina(String rotina) {
		System.out.println(ValorFixo.INICIO_DA_EXECUCAO_DA_ROTINA.concat(rotina).concat(" as ")
				.concat(ValidadorUtil.transformarDataNoFormatoDDmmYYYYHHmmss(new Date())));
	}

	public static void fimRotina(String rotina) {
		System.out.println(ValorFixo.FIM_DA_EXECUCAO_DA_ROTINA.concat(rotina).concat(" as ")
				.concat(ValidadorUtil.transformarDataNoFormatoDDmmYYYYHHmmss(new Date())));
	}

	/**
	 * Insere um espaço antes de uma letra maiuscula da String. Ex:
	 * acrescentaEspacoAntesDeLetraMaiuscula -> acrescenta Espaco Antes De Letra
	 * Maiuscula
	 * 
	 * @param string
	 */
	public static String acrescentaEspacoAntesDeLetraMaiuscula(String string) {
		String novaString = "";
		int contador = string.length();
		for (int i = 0; i < contador; i++) {
			if (Character.isUpperCase(string.charAt(i))) {
				novaString = novaString.concat(" ");
			}
			novaString = novaString.concat(string.substring(i, i + 1));
		}
		return transformarPrimeiraLetraParaMaiuscula(novaString);
	}

	public static String transformarPrimeiraLetraParaMaiuscula(String string) {
		String novaString = "";
		for (int i = 0; i < 1; i++) {
			novaString = string.substring(i, i + 1).toUpperCase();
		}
		novaString = novaString.concat(string.substring(1, string.length()));
		return novaString;
	}

	/**
	 * Caso a String recebida tenha o tamanho igual a 1, este metodo acrescenta
	 * um zero a esquerda para que a String fique com 2 algarismos
	 * 
	 * @param string
	 * @return
	 */
	public static String acrescentarZeroAEsquerdaString(String string) {
		if (string.length() == 1) {
			string = "0".concat(string);
		}
		return string;
	}

	/**
	 * A agência cedente constante das posições 20 a 23 do código de barras -
	 * (campo livre), deverá ser informada nas posições 374 a 377 - campo
	 * Informações Complementares, bem como nas posições 99 a 103 - campo código
	 * da agência do fornecedor, do Registro de Transação, e, adotar módulo 11,
	 * com base 7 para cálculo do dígito da agência, o qual deverá ser informado
	 * na posição 104 do Registro de Transação, vide modo de cálculo na página
	 * 28;
	 * 
	 * @param codAgencia
	 * @return
	 */
	public static String calcularDigitoAgenciaFornecedor(String codAgencia) {

		Integer quartoNumero = Integer.valueOf(codAgencia.substring(3, 4));
		Integer terceiroNumero = Integer.valueOf(codAgencia.substring(2, 3));
		Integer segundoNumero = Integer.valueOf(codAgencia.substring(1, 2));
		Integer primeiroNumero = Integer.valueOf(codAgencia.substring(0, 1));

		Integer multiplicacao1 = quartoNumero * 2;
		Integer multiplicacao2 = terceiroNumero * 3;
		Integer multiplicacao3 = segundoNumero * 4;
		Integer multiplicacao4 = primeiroNumero * 5;

		Integer soma = multiplicacao1 + multiplicacao2 + multiplicacao3 + multiplicacao4;

		Integer resultadoDaDivisao = soma / 11;
		Integer restoDaDivisao = soma % 11;

		String digito;

		// Nota: Se o resto da divisão for 0 (zero), o dígito será igual a zero
		// (0),
		// e se o resto for 1 (um), o dígito poderá ser igual a zero ou “P”.
		if (restoDaDivisao.equals(0) || restoDaDivisao.equals(1)) {
			digito = "0";
		} else {
			digito = String.valueOf(11 - restoDaDivisao);
		}

		return digito;
	}

	/**
	 * PagForLayout Pág 39 A Conta-Corrente constante das posições 37 a 43 do
	 * código de barras - (campo livre), deverá ser informada nas posições 391 a
	 * 397 - campo Informações Complementares, bem como na posição 105 a 117 -
	 * campos ContaCorrente do fornecedor, do Registro de Transação, e, adotar o
	 * módulo 11 com base 7, para cálculo do dígito da Conta-Corrente, o qual
	 * deverá ser informado na posição 118 - campo dígito da Conta-Corrente e,
	 * quanto à posição 119 do Registro de Transação, deverá permanecer branco,
	 * vide modo de cálculo na página 28.
	 * 
	 * @param contaCorrente
	 * @return
	 */
	public static String calcularDigitoContaCorrenteFornecedor(String contaCorrente) {

		/*
		 * Integer decimoTerceiroNumero =
		 * Integer.valueOf(contaCorrente.substring(12, 13)); Integer
		 * decimoSegundoNumero = Integer.valueOf(contaCorrente.substring(11,
		 * 12)); Integer decimoPrimeiroNumero =
		 * Integer.valueOf(contaCorrente.substring(10, 11)); Integer
		 * decimoNumero = Integer.valueOf(contaCorrente.substring(9, 10));
		 * Integer nonoNumero = Integer.valueOf(contaCorrente.substring(8, 9));
		 * Integer oitavoNumero = Integer.valueOf(contaCorrente.substring(7,
		 * 8));
		 */
		Integer setimoNumero = Integer.valueOf(contaCorrente.substring(6, 7));
		Integer sextoNumero = Integer.valueOf(contaCorrente.substring(5, 6));
		Integer quintoNumero = Integer.valueOf(contaCorrente.substring(4, 5));
		Integer quartoNumero = Integer.valueOf(contaCorrente.substring(3, 4));
		Integer terceiroNumero = Integer.valueOf(contaCorrente.substring(2, 3));
		Integer segundoNumero = Integer.valueOf(contaCorrente.substring(1, 2));
		Integer primeiroNumero = Integer.valueOf(contaCorrente.substring(0, 1));

		/*
		 * Integer multiplicacao1 = decimoTerceiroNumero*2; Integer
		 * multiplicacao2 = decimoSegundoNumero*3; Integer multiplicacao3 =
		 * decimoPrimeiroNumero*4; Integer multiplicacao4 = decimoNumero*5;
		 * Integer multiplicacao5 = nonoNumero*6; Integer multiplicacao6 =
		 * oitavoNumero*7; Integer multiplicacao7 = setimoNumero*2; Integer
		 * multiplicacao8 = sextoNumero*3; Integer multiplicacao9 =
		 * quintoNumero*4; Integer multiplicacao10 = quartoNumero*5; Integer
		 * multiplicacao11 = terceiroNumero*6; Integer multiplicacao12 =
		 * segundoNumero*7; Integer multiplicacao13 = primeiroNumero*2
		 */;

		Integer multiplicacao1 = setimoNumero * 2;
		Integer multiplicacao2 = sextoNumero * 3;
		Integer multiplicacao3 = quintoNumero * 4;
		Integer multiplicacao4 = quartoNumero * 5;
		Integer multiplicacao5 = terceiroNumero * 6;
		Integer multiplicacao6 = segundoNumero * 7;
		Integer multiplicacao7 = primeiroNumero * 2;

		/*
		 * Integer soma = multiplicacao1 + multiplicacao2 + multiplicacao3 +
		 * multiplicacao4 + multiplicacao5 + multiplicacao6 + multiplicacao7 +
		 * multiplicacao8 + multiplicacao9 + multiplicacao10 + multiplicacao11 +
		 * multiplicacao12 + multiplicacao13;
		 */
		Integer soma = multiplicacao1 + multiplicacao2 + multiplicacao3 + multiplicacao4 + multiplicacao5
				+ multiplicacao6 + multiplicacao7;

		Integer resultadoDaDivisao = soma / 11;
		Integer restoDaDivisao = soma % 11;

		String digito;

		// Nota: Se o resto da divisão for 0 (zero), o dígito será igual a zero
		// (0),
		// e se o resto for 1 (um), o dígito poderá ser igual a zero ou “P”.
		if (restoDaDivisao.equals(0) || restoDaDivisao.equals(1)) {
			digito = "0";
		} else {
			digito = String.valueOf(11 - restoDaDivisao);
		}

		// Concatenar com zero caso o "digito" tenha apenas uma casa decimal.
		// O resultado deve conter duas casas decimais
		if (digito.toString().length() < 2) {
			digito = "0".concat(digito.toString());
		}

		return digito;
	}

	public static Boolean ehZero(BigDecimal vlr) {
		String zero = "0";
		String zeroComCasaDecimal = "0.00";

		if (vlr.toString().length() > 1) {
			if (vlr.toString().equals(zeroComCasaDecimal)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (vlr.toString().equals(zero)) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Adiciona um '.' (ponto) antes dos dois ultimos números do valor, para
	 * indicar as casa decimais. Ex: valor = 660 valorRetorno = 6.60
	 * 
	 * @param valor
	 */
	public static BigDecimal adicionarPontoAntesDasDuasCasasDecimais(BigDecimal valor) {
		return valor.movePointLeft(2);
	}

	/**
	 * @author Adriano Troca ponto por vírgula em variaveis do tipo big decimal.
	 */
	public static String trocarPontoPorVirgulaBigDecimal(BigDecimal valor) {
		String strValor = valor.toString();
		return strValor.replace(".", ",");
	}

	/***
	 * Matheus Carolino
	 * 
	 * Insere espaço em branco ate o tamanho total do campo, passado por
	 * parâmetro Ex.: campo = "123" / tamanhoDoCampo = 5
	 * 
	 * Resultado.: campo = "123  "
	 * 
	 * @param campo
	 * @param tamanhoDoCampo
	 * @return
	 */
	public static String inserirEspacoEmBranco(String campo, Integer tamanhoDoCampo) {
		for (int i = 0; campo.length() < tamanhoDoCampo; i++) {
			campo = campo.concat(" ");
		}
		return campo;
	}

	public static String ehVazio(String campo) {
		if (campo.isEmpty()) {
			return null;
		} else {
			return campo;
		}
	}

	/**
	 * @author Adriano
	 * @since 18/07/2016 Converte Virgula ',' em Ponto '.'.
	 * 
	 */
	public static String trocaVirgulaPorPonto(String valor) {
		return valor = valor.replace(",", ".");
	}

	/**
	 * @author Adriano
	 * @since 18/07/2016 Transforma String formatada 0.00 em BigDecimal
	 * 
	 */
	public static BigDecimal transformarStringFormatadaEmBigDecimal(String valorString) {
		if (valorString == null || valorString.isEmpty()) {
			return new BigDecimal(0);
		} else {
			return new BigDecimal(valorString);
		}
	}
}
