package br.edu.pucpr.gestaoauto.api.dto.notificacao;

import br.edu.pucpr.gestaoauto.model.notificacao.TipoNotificacao;

public class NotificacaoDTO {
    private Integer codigo;
    private TipoNotificacao tipoNotificacao;
    private String mensagem;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public TipoNotificacao getTipoNotificacao(){
        return this.tipoNotificacao;
    }
    public void setTipoNotificacao(TipoNotificacao tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}