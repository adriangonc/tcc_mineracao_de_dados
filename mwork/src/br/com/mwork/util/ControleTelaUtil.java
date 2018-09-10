package br.com.mwork.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class ControleTelaUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5265333594339422884L;

	@Getter
	@Setter
	private Boolean iptDataFim;
	@Getter
	@Setter
	private Boolean coluna1;
	@Getter
	@Setter
	private Boolean coluna2;
	@Getter
	@Setter
	private Boolean coluna3;
	@Getter
	@Setter
	private Boolean coluna4;
	@Getter
	@Setter
	private Boolean coluna5;
	@Getter
	@Setter
	private Boolean coluna6;
	@Getter
	@Setter
	private Boolean coluna7;
	@Getter
	@Setter
	private Boolean coluna8;
	@Getter
	@Setter
	private Boolean coluna9;
	@Getter
	@Setter
	private Boolean coluna10;
	@Getter
	@Setter
	private Boolean coluna11;
	@Getter
	@Setter
	private Boolean coluna12;
	@Getter
	@Setter
	private Boolean coluna13;
	@Getter
	@Setter
	private Boolean coluna14;
	@Getter
	@Setter
	private Boolean coluna15;
	@Getter
	@Setter
	private Boolean coluna16;
	@Getter
	@Setter
	private Boolean coluna17;
	@Getter
	@Setter
	private Boolean coluna18;
	@Getter
	@Setter
	private Boolean coluna19;
	@Getter
	@Setter
	private Boolean coluna20;
	@Getter
	@Setter
	private Boolean coluna21;
	@Getter
	@Setter
	private Boolean coluna22;
	@Getter
	@Setter
	private Boolean coluna23;
	@Getter
	@Setter
	private Boolean coluna24;

	@Getter
	@Setter
	private String mes1;
	@Getter
	@Setter
	private String mes2;
	@Getter
	@Setter
	private String mes3;
	@Getter
	@Setter
	private String mes4;
	@Getter
	@Setter
	private String mes5;
	@Getter
	@Setter
	private String mes6;
	@Getter
	@Setter
	private String mes7;
	@Getter
	@Setter
	private String mes8;
	@Getter
	@Setter
	private String mes9;
	@Getter
	@Setter
	private String mes10;
	@Getter
	@Setter
	private String mes11;
	@Getter
	@Setter
	private String mes12;
	@Getter
	@Setter
	private String mes13;
	@Getter
	@Setter
	private String mes14;
	@Getter
	@Setter
	private String mes15;
	@Getter
	@Setter
	private String mes16;
	@Getter
	@Setter
	private String mes17;
	@Getter
	@Setter
	private String mes18;
	@Getter
	@Setter
	private String mes19;
	@Getter
	@Setter
	private String mes20;
	@Getter
	@Setter
	private String mes21;
	@Getter
	@Setter
	private String mes22;
	@Getter
	@Setter
	private String mes23;
	@Getter
	@Setter
	private String mes24;
	@Getter
	@Setter
	private String mes;
	@Getter
	@Setter
	private int primeiroMes;
	@Getter
	@Setter
	private int primeiroAno;
	@Getter
	@Setter
	private int mesAtual;

	public void habilitaColunasRelatorio(Date mesInicio, Date mesFim) {
		mesAtual = new Date().getMonth();

		int contAno = mesFim.getYear() - mesInicio.getYear();
		int contMes = mesFim.getMonth() - mesInicio.getMonth();
		if (contAno > 0) {
			contMes = contMes + 12;
		}
		if (contMes == 0) {
			contMes = 1;
		}
		primeiroMes = mesInicio.getMonth();
		primeiroAno = mesInicio.getYear();
		String anoDoisDigitos = Integer.toString(primeiroAno).substring(1, 3);
		primeiroAno = Integer.parseInt(anoDoisDigitos);
		verificaMes(contMes);
		/*
		 * if (contAno == 2) { exibe24Meses(); } else if (contAno == 1) {
		 * exibe12Meses(); verificaMesSeAno1(contMes); } else {
		 * verificaMes(contMes); }
		 */

	}

	private void verificaMes(int contMes) {
		switch (contMes) {
		case 24:
			habilitaMes24();
			break;
		case 23:
			habilitaMes23();
			break;
		case 22:
			habilitaMes22();
			break;
		case 21:
			habilitaMes21();
			break;
		case 20:
			habilitaMes20();
			break;
		case 19:
			habilitaMes19();
			break;
		case 18:
			habilitaMes18();
			break;
		case 17:
			habilitaMes17();
			break;
		case 16:
			habilitaMes16();
			break;
		case 15:
			habilitaMes15();
			break;
		case 14:
			habilitaMes14();
			break;
		case 13:
			habilitaMes13();
			break;
		case 12:
			habilitaMes12();
			break;
		case 11:
			habilitaMes11();
			break;
		case 10:
			habilitaMes10();
			break;
		case 9:
			habilitaMes9();
			break;
		case 8:
			habilitaMes8();
			break;
		case 7:
			habilitaMes7();
			break;
		case 6:
			habilitaMes6();
			break;
		case 5:
			habilitaMes5();
			break;
		case 4:
			habilitaMes4();
			break;
		case 3:
			habilitaMes3();
			break;
		case 2:
			habilitaMes2();
			break;
		case 1:
			habilitaMes1();
			break;

		default:
			break;
		}
	}

	public String verificarMes(int mes, int ano) {

		String nomeMes;
		nomeMes = "";
		switch (mes) {
		case 0:
			nomeMes = "Janeiro " + ano + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 1:
			nomeMes = "Fevereiro " + ano + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 2:
			nomeMes = "Março " + ano + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 3:
			nomeMes = "Abril " + ano + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 4:
			nomeMes = "Maio " + ano + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 5:
			nomeMes = "Junho " + ano + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 6:
			nomeMes = "Julho " + ano + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 7:
			nomeMes = "Agosto " + ano + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 8:
			nomeMes = "Setembro " + ano + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 9:
			nomeMes = "Outubro " + ano + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 10:
			nomeMes = "Novembro " + ano + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 11:
			nomeMes = "Dezembro " + ano + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 12:
			nomeMes = "Janeiro " + (ano + 1) + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 13:
			nomeMes = "Fevereiro " + (ano + 1) + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 14:
			nomeMes = "Março " + (ano + 1) + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 15:
			nomeMes = "Abril " + (ano + 1) + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 16:
			nomeMes = "Maio " + (ano + 1) + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 17:
			nomeMes = "Junho " + (ano + 1) + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 18:
			nomeMes = "Julho " + (ano + 1) + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 19:
			nomeMes = "Agosto " + (ano + 1) + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 20:
			nomeMes = "Setembro " + (ano + 1) + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 21:
			nomeMes = "Outubro " + (ano + 1) + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 22:
			nomeMes = "Novembro " + (ano + 1) + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;
		case 23:
			nomeMes = "Dezembro " + (ano + 1) + " ";
			if (mes == mesAtual) {
				nomeMes = (nomeMes + " " + "(Aberto)");
			}
			break;

		default:
			break;
		}
		return nomeMes;
	}

	private void habilitaMes24() {
		habilitaMes23();
		setColuna24(true);
		setMes24(verificarMes(primeiroMes + 11, primeiroAno));
	}

	private void habilitaMes23() {
		habilitaMes22();
		setColuna23(true);
		setMes23(verificarMes(primeiroMes + 10, primeiroAno));
	}

	private void habilitaMes22() {
		habilitaMes21();
		setColuna22(true);
		setMes22(verificarMes(primeiroMes + 9, primeiroAno));
	}

	private void habilitaMes21() {
		habilitaMes20();
		setColuna21(true);
		setMes21(verificarMes(primeiroMes + 8, primeiroAno));
	}

	private void habilitaMes20() {
		habilitaMes19();
		setColuna20(true);
		setMes20(verificarMes(primeiroMes + 7, primeiroAno));
	}

	private void habilitaMes19() {
		habilitaMes18();
		setColuna19(true);
		setMes19(verificarMes(primeiroMes + 6, primeiroAno));
	}

	private void habilitaMes18() {
		habilitaMes17();
		setColuna18(true);
		setMes18(verificarMes(primeiroMes + 5, primeiroAno));
	}

	private void habilitaMes17() {
		habilitaMes16();
		setColuna17(true);
		setMes17(verificarMes(primeiroMes + 4, primeiroAno));
	}

	private void habilitaMes16() {
		habilitaMes15();
		setColuna16(true);
		setMes16(verificarMes(primeiroMes + 3, primeiroAno));
	}

	private void habilitaMes15() {
		habilitaMes14();
		setColuna15(true);
		setMes15(verificarMes(primeiroMes + 2, primeiroAno));
	}

	private void habilitaMes14() {
		habilitaMes13();
		setColuna14(true);
		setMes14(verificarMes(primeiroMes + 1, primeiroAno));
	}

	private void habilitaMes13() {
		habilitaMes12();
		setColuna13(true);
		setMes13(verificarMes(primeiroMes, primeiroAno));
	}

	private void habilitaMes12() {
		habilitaMes11();
		setColuna12(true);
		setMes12(verificarMes(primeiroMes + 11, primeiroAno));
	}

	private String ano1() {
		String ano = "";
		ano = Integer.toString(primeiroAno, primeiroAno);
		return null;
	}

	private String ano2() {
		String ano = "";
		ano = Integer.toString(primeiroAno, primeiroAno);
		return null;
	}

	private void habilitaMes11() {
		habilitaMes10();
		setColuna11(true);
		setMes11(verificarMes(primeiroMes + 10, primeiroAno));
	}

	private void habilitaMes10() {
		habilitaMes9();
		setColuna10(true);
		setMes10(verificarMes(primeiroMes + 9, primeiroAno));
	}

	private void habilitaMes9() {
		habilitaMes8();
		setColuna9(true);
		setMes9(verificarMes(primeiroMes + 8, primeiroAno));
	}

	private void habilitaMes8() {
		habilitaMes7();
		setColuna8(true);
		setMes8(verificarMes(primeiroMes + 7, primeiroAno));
	}

	private void habilitaMes7() {
		habilitaMes6();
		setColuna7(true);
		setMes7(verificarMes(primeiroMes + 6, primeiroAno));
	}

	private void habilitaMes6() {
		habilitaMes5();
		setColuna6(true);
		setMes6(verificarMes(primeiroMes + 5, primeiroAno));
	}

	private void habilitaMes5() {
		habilitaMes4();
		setColuna5(true);
		setMes5(verificarMes(primeiroMes + 4, primeiroAno));
	}

	private void habilitaMes4() {
		habilitaMes3();
		setColuna4(true);
		setMes4(verificarMes(primeiroMes + 3, primeiroAno));
	}

	private void habilitaMes3() {
		habilitaMes2();
		setColuna3(true);
		setMes3(verificarMes(primeiroMes + 2, primeiroAno));
	}

	private void habilitaMes2() {
		habilitaMes1();
		setColuna2(true);
		setMes2(verificarMes(primeiroMes + 1, primeiroAno));
	}

	private void habilitaMes1() {
		setColuna1(true);
		setMes1(verificarMes(primeiroMes, primeiroAno));
	}

	/*
	 * private void exibe12Meses() { setColuna1(true); setColuna2(true);
	 * setColuna3(true); setColuna4(true); setColuna5(true); setColuna6(true);
	 * setColuna7(true); setColuna8(true); setColuna9(true); setColuna10(true);
	 * setColuna11(true); setColuna12(true); }
	 */

	/*
	 * private void exibe24Meses() { exibe12Meses(); setColuna13(true);
	 * setColuna14(true); setColuna15(true); setColuna16(true);
	 * setColuna17(true); setColuna18(true); setColuna19(true);
	 * setColuna20(true); setColuna21(true); setColuna22(true);
	 * setColuna23(true); setColuna24(true); }
	 */
	/*
	 * private void verificaMesSeAno1(int contMes) { switch (contMes) { case 11:
	 * habilitaMes12(); break; case 10: setColuna13(true); setColuna14(true);
	 * setColuna15(true); setColuna16(true); setColuna17(true);
	 * setColuna18(true); setColuna19(true); setColuna20(true);
	 * setColuna21(true); setColuna22(true); case 9: setColuna13(true);
	 * setColuna14(true); setColuna15(true); setColuna16(true);
	 * setColuna17(true); setColuna18(true); setColuna19(true);
	 * setColuna20(true); setColuna21(true); case 8: setColuna13(true);
	 * setColuna14(true); setColuna15(true); setColuna16(true);
	 * setColuna17(true); setColuna18(true); setColuna19(true);
	 * setColuna20(true); case 7: setColuna13(true); setColuna14(true);
	 * setColuna15(true); setColuna16(true); setColuna17(true);
	 * setColuna18(true); setColuna19(true); case 6: setColuna13(true);
	 * setColuna14(true); setColuna15(true); setColuna16(true);
	 * setColuna17(true); setColuna18(true); case 5: setColuna13(true);
	 * setColuna14(true); setColuna15(true); setColuna16(true);
	 * setColuna17(true); case 4: setColuna13(true); setColuna14(true);
	 * setColuna15(true); setColuna16(true); case 3: setColuna13(true);
	 * setColuna14(true); setColuna15(true); case 2: setColuna13(true);
	 * setColuna14(true); case 1: setColuna13(true); default: break; } }
	 */

	public void nomearMes(int mesInicio, int mesFim, int ano) {
		int contMes = mesFim - mesInicio;
		switch (contMes) {
		case 1:
			setMes1("Jan" + " " + ano);
			setMes2("Fev" + " " + ano);
			setMes3("Mar" + " " + ano);
			setMes4("Abr" + " " + ano);
			setMes5("Mai" + " " + ano);
			setMes6("Jun" + " " + ano);
			setMes7("Jul" + " " + ano);
			setMes8("Ago" + " " + ano);
			setMes9("Set" + " " + ano);
			setMes10("Out" + " " + ano);
			setMes11("Nov" + " " + ano);
			setMes12("Dez" + " " + ano);
			setMes13("Jan" + " " + ano + 1);
			setMes14("Fev" + " " + ano + 1);
			setMes15("Mar" + " " + ano + 1);
			setMes16("Abr" + " " + ano + 1);
			setMes17("Mai" + " " + ano + 1);
			setMes18("Jun" + " " + ano + 1);
			setMes19("Jul" + " " + ano + 1);
			setMes20("Ago" + " " + ano + 1);
			setMes21("Set" + " " + ano + 1);
			setMes22("Out" + " " + ano + 1);
			setMes23("Nov" + " " + ano + 1);
			setMes24("Dez" + " " + ano + 1);
		}

	}

	public void habilitarDataFim() {
		setIptDataFim(false);
	}

}
