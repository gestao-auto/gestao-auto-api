package br.edu.pucpr.gestaoauto.api.dto.usuario;

public class UsuarioDTO {
    private Integer codigo;
    private String email;
    private String senha;
	private String dataAceiteTermoUso;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDataAceiteTermoUso() {
		return dataAceiteTermoUso;
	}

	public void setDataAceiteTermoUso(String dataAceiteTermoUso) {
		this.dataAceiteTermoUso = dataAceiteTermoUso;
	}
}