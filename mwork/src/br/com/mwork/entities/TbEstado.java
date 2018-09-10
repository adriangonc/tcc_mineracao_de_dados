package br.com.mwork.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_estado database table.
 * 
 */
@Entity
@Table(name="tb_estado")
@NamedQuery(name="TbEstado.findAll", query="SELECT t FROM TbEstado t")
public class TbEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="nome_estado")
	private String nomeEstado;

	public TbEstado() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeEstado() {
		return this.nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

}