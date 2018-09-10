package br.com.mwork.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tb_informacoes_usuario database table.
 * 
 */
@Entity
@Table(name = "tb_informacoes_usuario")
@NamedQuery(name = "TbInformacoesUsuario.findAll", query = "SELECT t FROM TbInformacoesUsuario t")
public class TbInformacoesUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	// @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String cidade;

	@Column(name = "cod_estado")
	private Integer codEstado;

	@Column(name = "cod_ibge_cidade")
	private Integer codIbgeCidade;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_alteracao")
	private Date dataAlteracao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@Column(name = "descricao_atividades")
	private String descricaoAtividades;

	private String email;

	private String estado;

	@Column(name = "media_qualificacao")
	private Integer mediaQualificacao;

	private String prestador;

	private String telefone;

	@Column(name = "tipo_conta")
	private Integer tipoConta;

	private String atividade;

	@Column(name = "endereco_eletronico")
	private String enderecoEletronico;

	public String getEnderecoEletronico() {
		return enderecoEletronico;
	}

	public void setEnderecoEletronico(String enderecoEletronico) {
		this.enderecoEletronico = enderecoEletronico;
	}

	public TbInformacoesUsuario() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Integer getCodEstado() {
		return this.codEstado;
	}

	public void setCodEstado(Integer codEstado) {
		this.codEstado = codEstado;
	}

	public Integer getCodIbgeCidade() {
		return this.codIbgeCidade;
	}

	public void setCodIbgeCidade(Integer codIbgeCidade) {
		this.codIbgeCidade = codIbgeCidade;
	}

	public Date getDataAlteracao() {
		return this.dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDescricaoAtividades() {
		return this.descricaoAtividades;
	}

	public void setDescricaoAtividades(String descricaoAtividades) {
		this.descricaoAtividades = descricaoAtividades;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getMediaQualificacao() {
		return this.mediaQualificacao;
	}

	public void setMediaQualificacao(Integer mediaQualificacao) {
		this.mediaQualificacao = mediaQualificacao;
	}

	public String getPrestador() {
		return this.prestador;
	}

	public void setPrestador(String prestador) {
		this.prestador = prestador;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getTipoConta() {
		return this.tipoConta;
	}

	public void setTipoConta(Integer tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

}