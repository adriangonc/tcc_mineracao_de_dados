package br.com.mwork.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_mensagens database table.
 * 
 */
@Entity
@Table(name="tb_mensagens")
@NamedQuery(name="TbMensagen.findAll", query="SELECT t FROM TbMensagen t")
public class TbMensagen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_mensagem")
	private Long idMensagem;

	@Temporal(TemporalType.DATE)
	@Column(name="data_envio")
	private Date dataEnvio;

	@Column(name="id_usuario_destinatario")
	private Integer idUsuarioDestinatario;

	@Column(name="id_usuario_remetente")
	private Integer idUsuarioRemetente;

	private String mensagem;

	@Column(name="mensagem_visualizada")
	private String mensagemVisualizada;

	public TbMensagen() {
	}

	public Long getIdMensagem() {
		return this.idMensagem;
	}

	public void setIdMensagem(Long idMensagem) {
		this.idMensagem = idMensagem;
	}

	public Date getDataEnvio() {
		return this.dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Integer getIdUsuarioDestinatario() {
		return this.idUsuarioDestinatario;
	}

	public void setIdUsuarioDestinatario(Integer idUsuarioDestinatario) {
		this.idUsuarioDestinatario = idUsuarioDestinatario;
	}

	public Integer getIdUsuarioRemetente() {
		return this.idUsuarioRemetente;
	}

	public void setIdUsuarioRemetente(Integer idUsuarioRemetente) {
		this.idUsuarioRemetente = idUsuarioRemetente;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagemVisualizada() {
		return this.mensagemVisualizada;
	}

	public void setMensagemVisualizada(String mensagemVisualizada) {
		this.mensagemVisualizada = mensagemVisualizada;
	}

}