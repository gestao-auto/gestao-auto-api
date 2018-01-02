package br.edu.pucpr.gestaoauto.model.usuario;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuario", catalog = "gestao_auto")
public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = -4812102239033165636L;

	private Integer codigo;
	private String email;
	private String senha;
	private String tokenRecuperarSenha;
	private Date dataAceiteTermoUso;
	private byte[] foto;

	public Usuario() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "codusuario", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codusuario) {
		this.codigo = codusuario;
	}

	@Column(name = "email", length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "senha", length = 45)
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name = "tokenrecuperarsenha", length = 45)
	public String getTokenRecuperarSenha() {
		return this.tokenRecuperarSenha;
	}

	public void setTokenRecuperarSenha(String tokenRecuperarSenha) {
		this.tokenRecuperarSenha = tokenRecuperarSenha;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dataceitetermouso", length = 10)
	public Date getDataAceiteTermoUso() {
		return this.dataAceiteTermoUso;
	}

	public void setDataAceiteTermoUso(Date dataAceiteTermoUso) {
		this.dataAceiteTermoUso = dataAceiteTermoUso;
	}

	@Column(name = "foto")
	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
}
