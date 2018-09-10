package br.com.mwork.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the view_cidades database table.
 * 
 */
@Entity
@Table(name="view_cidades")
@NamedQuery(name="ViewCidade.findAll", query="SELECT v FROM ViewCidade v")
public class ViewCidade implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codigoibge;

	@Id
	private Integer id;

	private String nome;

	private Integer ufid;

	public ViewCidade() {
	}

	public Integer getCodigoibge() {
		return this.codigoibge;
	}

	public void setCodigoibge(Integer codigoibge) {
		this.codigoibge = codigoibge;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getUfid() {
		return this.ufid;
	}

	public void setUfid(Integer ufid) {
		this.ufid = ufid;
	}

}