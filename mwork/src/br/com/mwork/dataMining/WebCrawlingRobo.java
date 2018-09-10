package br.com.mwork.dataMining;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import br.com.mwork.entities.TbDadosMinerado;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import lombok.Getter;
import lombok.Setter;

public class WebCrawlingRobo extends WebCrawler {

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg" + "|png|mp3|mp3|zip|gz))$");

	String t1 = "Pintor";
	String t2 = "Design";
	String t3 = "pintor";
	String t4 = "Chur";
	String t5 = "Mototaxi";
	String t6 = "Pedreiro";
	String t7 = "Pintor";
	String t8 = "Babá";
	String t9 = "Carpinteiro";
	String t10 = "Mototaxista";
	String t11 = "Diarista";
	String t12 = "Técnicio informática";
	String t13 = "Contador";
	String t14 = "Pedreiro";
	String t15 = "Encanador";
	String t16 = "Garçon";
	String t17 = "Cozinheiro";
	String t18 = "Entregador";
	String t19 = "Office boy";
	String t20 = "Eletricista";
	String t21 = "Mecânico";
	String t22 = "Montador";
	String t23 = "Azulejista";
	String t24 = "Abatedor";
	String t25 = "Cuidador";
	String t26 = "Cantor";
	String t27 = "Taxista";
	String t28 = "Cabelereir";
	String t29 = "Maqueador";
	String t30 = "Manicure";
	String t31 = "Professor part";
	String t32 = "Músico";
	String t33 = "Programador";
	String t34 = "Márido de aluguel";
	String t35 = "Jardineiro";
	String t36 = "Higienizador";
	String t37 = "Seralheiro";
	String t38 = "Soldador";
	String t39 = "Adestrador";
	String t40 = "Segurança pa";
	String t41 = "Cerimonial";
	String t42 = "Confeiteira";
	String t43 = "Salgadeira";
	String t44 = "Costureir";
	String t45 = "Arquiteto";
	String t46 = "Corretor imobiliário";
	String t47 = "Ajundante de obras";
	String t48 = "Engenheiro";

	String caminho = "C:\\Users\\Sea\\Documents\\paginas_mineradas.txt";

	/*
	 * @Inject MinerarDadosRN minerarDadosRN;
	 */

	/*
	 * @Inject private EntityManager em;
	 */

	@Getter
	@Setter
	private TbDadosMinerado dados;

	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches() && href.startsWith("http://www.");
	}

	/**
	 * Função acionada apos a leitura da página encontrada pelo boot.
	 */

	int cont = 1;

	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			Set<WebURL> links = htmlParseData.getOutgoingUrls();

			String title = "";

			Scanner UrlScanner = new Scanner(url);
			while (UrlScanner.hasNextLine()) {
				String urlPagina = UrlScanner.nextLine().split(" ")[0]; // Pega
																		// o
																		// primeiro
																		// caractere
																		// da
																		// linha
				Document doc;
				try {
					doc = Jsoup.connect(urlPagina).get();
					title = doc.title();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			System.out.println("Url Processada: " + url);

			if (verificaTermos(title)) {
				System.out.println("URL: " + url);
				System.out.println("Titulo: " + title);
				System.out.println("Tamanho do texto: " + text.length());
				System.out.println("Tamanho do Html: " + html.length());
				System.out.println("Numero de links de saida: " + links.size());
				System.out
						.println("----------------------------------------------------------------------------------- "
								+ links.size());

				TbDadosMinerado dados = new TbDadosMinerado();

				int linksSaida = links.size();
				dados.setUrl(url);
				dados.setTitulo(title);
				dados.setLinksDeSaida(Integer.toString(linksSaida));
				try {
					escritor(caminho, dados);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
	}

	private boolean verificaTermos(String title) {
		return title.contains(t1) || title.contains(t2) || title.contains(t3) || title.contains(t4)
				|| title.contains(t5) || title.contains(t6) || title.contains(t8) || title.contains(t9)
				|| title.contains(t10) || title.contains(t11) || title.contains(t12) || title.contains(t13)
				|| title.contains(t14) || title.contains(t15) || title.contains(t16) || title.contains(t17)
				|| title.contains(t18) || title.contains(t19) || title.contains(t20) || title.contains(t21)
				|| title.contains(t22) || title.contains(t23) || title.contains(t24) || title.contains(t25)
				|| title.contains(t26) || title.contains(t27) || title.contains(t28) || title.contains(t29)
				|| title.contains(t30) || title.contains(t31) || title.contains(t32) || title.contains(t33)
				|| title.contains(t34) || title.contains(t35) || title.contains(t36) || title.contains(t37)
				|| title.contains(t38) || title.contains(t39) || title.contains(t40) || title.contains(t41)
				|| title.contains(t42) || title.contains(t43) || title.contains(t44) || title.contains(t45)
				|| title.contains(t46) || title.contains(t47) || title.contains(t48);
	}

	public void escritor(String path, TbDadosMinerado dados) throws IOException {

		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(path, true));

			String titulo = dados.getTitulo().replaceAll("|", "");

			writer.write(dados.getUrl() + "|" + titulo + "|"
					+ new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()) + "|" + dados.getLinksDeSaida());
			writer.newLine();
			/*
			 * writer.write(); writer.newLine(); writer.write();
			 * writer.newLine(); writer.write( + "|"); writer.newLine();
			 * writer.write("\n"); writer.newLine();
			 */

			writer.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally { // always close the file
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ioe2) {
					// just ignore it
				}
			}
		}

	}

}
