package br.com.mwork.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_dados_minerados database table.
 * 
 */
@Entity
@Table(name="tb_dados_minerados")
@NamedQuery(name="TbDadosMinerado.findAll", query="SELECT t FROM TbDadosMinerado t")
public class TbDadosMinerado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String url;

	private String dada;

	private Integer is;

	@Column(name="links_de_saida")
	private String linksDeSaida;

	private String titulo;

	public TbDadosMinerado() {
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDada() {
		return this.dada;
	}

	public void setDada(String dada) {
		this.dada = dada;
	}

	public Integer getIs() {
		return this.is;
	}

	public void setIs(Integer is) {
		this.is = is;
	}

	public String getLinksDeSaida() {
		return this.linksDeSaida;
	}

	public void setLinksDeSaida(String linksDeSaida) {
		this.linksDeSaida = linksDeSaida;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}