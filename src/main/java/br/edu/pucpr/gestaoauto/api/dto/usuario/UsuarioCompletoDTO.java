package br.edu.pucpr.gestaoauto.api.dto.usuario;

public class UsuarioCompletoDTO extends UsuarioDTO {
    private String tokenRecuperarSenha;
	private byte[] foto;

    public String getTokenRecuperarSenha() {
        return tokenRecuperarSenha;
    }

    public UsuarioCompletoDTO setTokenRecuperarSenha(String tokenRecuperarSenha) {
        this.tokenRecuperarSenha = tokenRecuperarSenha;
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