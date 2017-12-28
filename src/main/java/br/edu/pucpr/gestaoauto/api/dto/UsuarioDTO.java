package br.edu.pucpr.gestaoauto.api.dto;

import java.util.Date;

public class UsuarioDTO {
    private Integer codusuario;
    private String email;
    private String senha;
    private Date dataAceiteTermoUso;

    public Integer getCodusuario() {
        return codusuario;
    }

    public UsuarioDTO setCodusuario(Integer codusuario) {
        this.codusuario = codusuario;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsuarioDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSenha() {
        return this.senha;
    }

    public UsuarioDTO setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public Date getDataAceiteTermoUso() {
        return dataAceiteTermoUso;
    }

    public UsuarioDTO setDataAceiteTermoUso(Date dataAceiteTermoUso) {
        this.dataAceiteTermoUso = dataAceiteTermoUso;
        return this;
    }
}