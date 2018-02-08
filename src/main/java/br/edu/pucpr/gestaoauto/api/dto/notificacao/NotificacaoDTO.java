package br.edu.pucpr.gestaoauto.api.dto.notificacao;

public class NotificacaoDTO {
    private Integer codigo;
	private String tipoNotificacao;
    private String mensagem;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

	public String getTipoNotificacao() {
        return this.tipoNotificacao;
    }

	public void setTipoNotificacao(String tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}