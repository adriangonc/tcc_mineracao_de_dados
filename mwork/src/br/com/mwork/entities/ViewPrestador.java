package br.com.mwork.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the view_prestador database table.
 * 
 */
@Entity
@Table(name="view_prestador")
@NamedQuery(name="ViewPrestador.findAll", query="SELECT v FROM ViewPrestador v")
public @Data class ViewPrestador implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cidade;

	@Column(name="cod_estado")
	private Integer codEstado;

	@Column(name="cod_ibge_cidade")
	private Integer codIbgeCidade;

	@Temporal(TemporalType.DATE)
	@Column(name="data_alteracao")
	private Date dataAlteracao;

	@Temporal(TemporalType.DATE)
	@Column(name="data_cadastro")
	private Date dataCadastro;

	@Column(name="descricao_atividades")
	private String descricaoAtividades;

	private String email;

	private String estado;

	@Id
	private Integer id;

	@Column(name="media_qualificacao")
	private Integer mediaQualificacao;

	private String name;

	private String prestador;

	private String role;

	private String telefone;
	
	@Column(name="endereco_eletronico")
	private String enderecoEletronico;

	public ViewPrestador() {
	}

}