package br.com.mwork.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_atividade_usuario database table.
 * 
 */
@Entity
@Table(name="tb_atividade_usuario")
@NamedQuery(name="TbAtividadeUsuario.findAll", query="SELECT t FROM TbAtividadeUsuario t")
public class TbAtividadeUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbAtividadeUsuarioPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="data_cadastro")
	private Date dataCadastro;

	@Column(name="descricao_atividade")
	private String descricaoAtividade;

	public TbAtividadeUsuario() {
	}

	public TbAtividadeUsuarioPK getId() {
		return this.id;
	}

	public void setId(TbAtividadeUsuarioPK id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDescricaoAtividade() {
		return this.descricaoAtividade;
	}

	public void setDescricaoAtividade(String descricaoAtividade) {
		this.descricaoAtividade = descricaoAtividade;
	}

}