package br.com.mwork.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tb_atividade_usuario database table.
 * 
 */
@Embeddable
public class TbAtividadeUsuarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_usuario", insertable=false, updatable=false)
	private Integer idUsuario;

	@Column(name="id_atividade", insertable=false, updatable=false)
	private Integer idAtividade;

	public TbAtividadeUsuarioPK() {
	}
	public Integer getIdUsuario() {
		return this.idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdAtividade() {
		return this.idAtividade;
	}
	public void setIdAtividade(Integer idAtividade) {
		this.idAtividade = idAtividade;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TbAtividadeUsuarioPK)) {
			return false;
		}
		TbAtividadeUsuarioPK castOther = (TbAtividadeUsuarioPK)other;
		return 
			this.idUsuario.equals(castOther.idUsuario)
			&& this.idAtividade.equals(castOther.idAtividade);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUsuario.hashCode();
		hash = hash * prime + this.idAtividade.hashCode();
		
		return hash;
	}
}