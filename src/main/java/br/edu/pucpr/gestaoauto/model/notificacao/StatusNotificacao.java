package br.edu.pucpr.gestaoauto.model.notificacao;

public enum StatusNotificacao {
   GERADA("gerada")
    , LIDA("lida");

    private String status;

    StatusNotificacao(String status){
        this.status = status;
    }
}
