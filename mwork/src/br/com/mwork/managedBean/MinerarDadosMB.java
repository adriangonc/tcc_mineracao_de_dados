package br.com.mwork.managedBean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.mwork.RN.MinerarDadosRN;
import br.com.mwork.dataMining.WebCrawlingRobo;
import br.com.mwork.entities.TbDadosMinerado;
import br.com.mwork.util.ValorFixo;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
public class MinerarDadosMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5861046653302769885L;

	@Getter
	@Setter
	private TbDadosMinerado dados;

	@Getter
	@Setter
	public String urlUm;

	@Getter
	@Setter
	public String urlDois;

	@Getter
	@Setter
	public String urlTres;

	@PostConstruct
	public void iniciarInterface() {
		controleInicial();
	}

	public void controleInicial() {

	}

	public void minerar() {
		try {
			String crawlStorageFolder = "C:\\paginas_mineradas";
			int numberOfCrawlers = 7;

			CrawlConfig config = new CrawlConfig();
			config.setCrawlStorageFolder(crawlStorageFolder);

			/*
			 * Inicializa o controler do webcrawler.
			 */
			PageFetcher pageFetcher = new PageFetcher(config);
			RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
			RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
			CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

			String url1 = urlUm;

			controller.addSeed(url1 + "~servicos/");
			controller.addSeed(url1 + "~pintor/");
			controller.addSeed(url1);
			
			controller.start(WebCrawlingRobo.class, numberOfCrawlers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limpar() {
		urlUm = " ";
		urlDois = " ";
		urlTres = " ";
	}
	

}
