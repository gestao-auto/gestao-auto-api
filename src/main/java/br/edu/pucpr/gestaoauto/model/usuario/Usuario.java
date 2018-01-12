package br.edu.pucpr.gestaoauto.model.usuario;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario", catalog = "gestao_auto")
public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = -4812102239033165636L;

	private Integer codigo;
	private String email;
	private String senha;
	private String salt;
	private String tokenRecuperarSenha;
	private LocalDateTime dataAceiteTermoUso;
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

	@Column(unique = true, name = "email", length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "senha", length = 100)
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

	@Column(name = "dataceitetermouso", length = 10)
	public LocalDateTime getDataAceiteTermoUso() {
		return this.dataAceiteTermoUso;
	}

	public void setDataAceiteTermoUso(LocalDateTime dataAceiteTermoUso) {
		this.dataAceiteTermoUso = dataAceiteTermoUso;
	}

	@Column(name = "foto")
	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	@Column(name = "salt", length = 64)
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
