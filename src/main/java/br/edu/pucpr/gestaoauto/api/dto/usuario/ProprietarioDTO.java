package br.edu.pucpr.gestaoauto.api.dto.usuario;

import java.util.Date;

public class ProprietarioDTO {

	private Integer codigo;
	private Integer usuario;
	private String nome;
	private String sobrenome;
	private String sexo;
	private Date dataNascimento;

	public Integer getCodigo() {
		return codigo;
	}

	public ProprietarioDTO setCodigo(Integer codigo) {
		this.codigo = codigo;
		return this;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public ProprietarioDTO setUsuario(Integer usuario) {
		this.usuario = usuario;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public ProprietarioDTO setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public ProprietarioDTO setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
		return this;
	}

	public String getSexo() {
		return sexo;
	}

	public ProprietarioDTO setSexo(String sexo) {
		this.sexo = sexo;
		return this;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public ProprietarioDTO setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
		return this;
	}
}