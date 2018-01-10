package br.edu.pucpr.gestaoauto.api.dto.notificacao;

public class NotificacaoDTO {
    private Integer codigo;
    private String titulo;
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}