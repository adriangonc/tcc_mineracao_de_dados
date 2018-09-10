package br.com.mwork.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the parametro database table.
 * 
 */
@Entity
@NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "parametro_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_parametro_id")
	@SequenceGenerator(name = "seq_parametro_id", sequenceName = "seq_parametro_id", allocationSize = 1)
	private Integer parametroId;

	@Column(name = "data_alteracao")
	private Calendar dataAlteracao;

	@Column(name = "data_inclusao")
	private Calendar dataInclusao;

	@Column(name = "parametro_descricao")
	private String parametroDescricao;

	@Column(name = "parametro_tipo")
	private Integer parametroTipo;

	@Column(name = "parametro_valor")
	private String parametroValor;

	@Column(name = "usuario_alteracao")
	private String usuarioAlteracao;

	@Column(name = "usuario_inclusao")
	private String usuarioInclusao;

	public Parametro() {
	}

	public Integer getParametroId() {
		return this.parametroId;
	}

	public void setParametroId(Integer parametroId) {
		this.parametroId = parametroId;
	}

	public Calendar getDataAlteracao() {
		return this.dataAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Calendar getDataInclusao() {
		return this.dataInclusao;
	}

	public void setDataInclusao(Calendar dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getParametroDescricao() {
		return this.parametroDescricao;
	}

	public void setParametroDescricao(String parametroDescricao) {
		this.parametroDescricao = parametroDescricao;
	}

	public Integer getParametroTipo() {
		return this.parametroTipo;
	}

	public void setParametroTipo(Integer parametroTipo) {
		this.parametroTipo = parametroTipo;
	}

	public String getParametroValor() {
		return this.parametroValor;
	}

	public void setParametroValor(String parametroValor) {
		this.parametroValor = parametroValor;
	}

	public String getUsuarioAlteracao() {
		return this.usuarioAlteracao;
	}

	public void setUsuarioAlteracao(String usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getUsuarioInclusao() {
		return this.usuarioInclusao;
	}

	public void setUsuarioInclusao(String usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}

}