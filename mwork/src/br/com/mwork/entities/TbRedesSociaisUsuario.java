package br.com.mwork.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_redes_sociais_usuario database table.
 * 
 */
@Entity
@Table(name="tb_redes_sociais_usuario")
@NamedQuery(name="TbRedesSociaisUsuario.findAll", query="SELECT t FROM TbRedesSociaisUsuario t")
public class TbRedesSociaisUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="endereco_icone")
	private String enderecoIcone;

	@Column(name="link_rede_social")
	private String linkRedeSocial;

	@Column(name="nome_rede_social")
	private String nomeRedeSocial;

	public TbRedesSociaisUsuario() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnderecoIcone() {
		return this.enderecoIcone;
	}

	public void setEnderecoIcone(String enderecoIcone) {
		this.enderecoIcone = enderecoIcone;
	}

	public String getLinkRedeSocial() {
		return this.linkRedeSocial;
	}

	public void setLinkRedeSocial(String linkRedeSocial) {
		this.linkRedeSocial = linkRedeSocial;
	}

	public String getNomeRedeSocial() {
		return this.nomeRedeSocial;
	}

	public void setNomeRedeSocial(String nomeRedeSocial) {
		this.nomeRedeSocial = nomeRedeSocial;
	}

}