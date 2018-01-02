package br.edu.pucpr.gestaoauto.api.dto.usuario;

import java.util.Date;

public class UsuarioCompletoDTO extends UsuarioDTO{
    private Integer codusuario;
    private String email;
    private String senha;
    private String tokenRecuperarSenha;
    private Date dataAceiteTermoUso;
    private byte[] foto;

    public Integer getCodusuario() {
        return codusuario;
    }

    public UsuarioCompletoDTO setCodusuario(Integer codusuario) {
        this.codusuario = codusuario;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsuarioCompletoDTO setEmail(String email) {
        this.email = email;
        return this;
    }

   public String getSenha() {
        return this.senha;
    }

    public UsuarioCompletoDTO setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String getTokenRecuperarSenha() {
        return tokenRecuperarSenha;
    }

    public UsuarioCompletoDTO setTokenRecuperarSenha(String tokenRecuperarSenha) {
        this.tokenRecuperarSenha = tokenRecuperarSenha;
        return this;
    }

    public Date getDataAceiteTermoUso() {
        return dataAceiteTermoUso;
    }

    public UsuarioCompletoDTO setDataAceiteTermoUso(Date dataAceiteTermoUso) {
        this.dataAceiteTermoUso = dataAceiteTermoUso;
        return this;
    }

    public byte[] getFoto() {
        return foto;
    }

    public UsuarioCompletoDTO setFoto(byte[] foto) {
        this.foto = foto;
        return this;
    }
}