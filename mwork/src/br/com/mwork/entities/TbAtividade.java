package br.com.mwork.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the tb_atividades database table.
 * 
 */
@Entity
@Table(name="tb_atividades")
@NamedQuery(name="TbAtividade.findAll", query="SELECT t FROM TbAtividade t")
public @Data class TbAtividade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_atividade_id")
	@SequenceGenerator(name="seq_atividade_id", sequenceName="seq_atividade_id", allocationSize=1)
	@Column(name="id_atividade")
	private Integer idAtividade;

	@Column(name="nome_atividade")
	private String nomeAtividade;

}