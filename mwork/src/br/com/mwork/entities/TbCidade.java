package br.com.mwork.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_cidade database table.
 * 
 */
@Entity
@Table(name="tb_cidade")
@NamedQuery(name="TbCidade.findAll", query="SELECT t FROM TbCidade t")
public class TbCidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Integer codigoibge;

	private String nome;

	private Integer ufid;

	public TbCidade() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigoibge() {
		return this.codigoibge;
	}

	public void setCodigoibge(Integer codigoibge) {
		this.codigoibge = codigoibge;
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