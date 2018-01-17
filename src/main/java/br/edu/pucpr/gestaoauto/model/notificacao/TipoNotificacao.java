package br.edu.pucpr.gestaoauto.model.notificacao;

public enum TipoNotificacao {
   REVISAO("revisao")
    , DOCUMENTO("documento")
    , RODIZIO("rodizio");

    private String tipo;

    TipoNotificacao(String tipo){
        this.tipo = tipo;
    }
}
